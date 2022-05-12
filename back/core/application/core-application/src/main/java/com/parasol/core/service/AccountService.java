package com.parasol.core.service;

import com.parasol.core.VO.Balance;
import com.parasol.core.api_model.*;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.utils.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ValidationService validationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionHistoryService transactionHistoryService;


    public String Create(@Valid AccountOpenRequest accountOpenRequest) {
        Client client = clientService.findById(accountOpenRequest.getCusNo());
        Account account = new Account();

        if (client == null)
            return null;

        account.setClient(client);
        account.setPassword(accountOpenRequest.getAccountPassword());
        account.setId(AccountManager.GenerateAccountNumber());

        return accountRepository.save(account).getId();
    }


    public AccountListQueryResultResponse getAllAccount(@Valid AccountListQueryRequest request) {
        Client client = clientService.findById(request.getCusNo());
        AccountListQueryResultResponse listresult = new AccountListQueryResultResponse();
        List<AccountNumber> result = new ArrayList<>();

        for (Account e : accountRepository.findByClient(client)) {
            AccountNumber ele = new AccountNumber();
            AccountInfo accountInfo = new AccountInfo();

            accountInfo.setAccountNumber(e.getId());
            ele.setAccountNumber(accountInfo.getAccountNumber());
            result.add(ele);
        }

        listresult.setAccounts(result);

        return listresult;
    }

    public AccountBalanceQueryResultResponse getBalanceWithPassword(AccountQueryRequest accountQueryRequest) {
        Optional<Account> account = accountRepository.findById(accountQueryRequest.getAccountNumber());
        AccountBalanceQueryResultResponse result = new AccountBalanceQueryResultResponse();

        if (account.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

//        validationService.equalPassword(accountQueryRequest.getAccountPassword(), account.get().getPassword());

        result.setBalance(account.get().getBalance());
        return result;
    }


    public Long getBalance(String accountNo) {
        Optional<Account> account = accountRepository.findById(accountNo);

        return account.get().getBalance();
    }

    @Transactional
    public DepositResponse deposit(@Valid AccountRequest request) {
        Long amount = request.getAmount();
        AccountInfo accountTo = request.getAccountTo();
        String nameFrom = request.getNameOpponent();

        if (accountTo == null) {
            throw new NullPointerException("AccountService :: deposit :: accountTo is null");
        }

        if (!StringUtils.hasText(nameFrom)) {
            throw new NullPointerException("AccountService :: deposit :: nameFrom is null");
        }

        String accountNumberTo = accountTo.getAccountNumber();
        Account depositAccount = accountRepository.findById(accountNumberTo).orElseThrow(IllegalStateException::new);

        Long beforeBalance = depositAccount.getBalance();
        Long afterBalance = beforeBalance + amount;

        depositAccount.setBalance(afterBalance);
        accountRepository.save(depositAccount);

        transactionHistoryService.createDepositHistory(accountNumberTo,
                accountNumberTo,
                nameFrom,
                amount);

        return DepositResponse.builder()
                .isSuccess(true)
                .build();
    }

    @Transactional
    public TransactionExecutionResultResponse withdraw(@Valid AccountWithdrawRequest request) {
        TransactionExecutionResultResponse resultResponse = new TransactionExecutionResultResponse();
        // from 계좌에서 출금
        Optional<Account> accountFrom = accountRepository.findById(request.getAccountFrom().getAccountNumber());

        if (accountFrom.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        validationService.equalPassword(request.getAccountPassword(), accountFrom.get().getPassword());

        Long fromBalance = validationService.calculateBalance(new Balance(accountFrom.get().getBalance() - request.getAmount()));
        // from 계좌에서 입금 금액만큼 빼기
        accountFrom.get().setBalance(fromBalance);

        transactionHistoryService.createWithdrawHistory(request.getAccountFrom().getAccountNumber(),
                request.getAccountFrom().getAccountNumber(),
                request.getNameOpponent(),
                request.getAmount());

        resultResponse.setSuccess(true);
        return resultResponse;
    }

    public TransactionExecutionResultResponse remit(@Valid AccountRequest request) {
        TransactionExecutionResultResponse resultResponse = new TransactionExecutionResultResponse();

        Optional<Account> accountTo = accountRepository.findById(request.getAccountTo().getAccountNumber());
        Optional<Account> accountFrom = accountRepository.findById(request.getAccountFrom().getAccountNumber());

        Long toBalance = validationService.calculateBalance(new Balance(accountTo.get().getBalance() + request.getAmount()));
        Long fromBalance = validationService.calculateBalance(new Balance(accountFrom.get().getBalance() - request.getAmount()));

        accountTo.get().setBalance(toBalance);
        accountFrom.get().setBalance(fromBalance);

        transactionHistoryService.createRemitHistory(request.getAccountFrom().getAccountNumber(),
                request.getAccountTo().getAccountNumber(),
                request.getNameOpponent(),
                request.getAmount());

        resultResponse.setSuccess(true);
        return resultResponse;
    }
}

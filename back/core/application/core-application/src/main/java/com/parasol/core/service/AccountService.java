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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public AccountOpenResponse createAccount(@Valid AccountOpenRequest request) {
        String accountNumber = AccountManager.generateAccountNumber();
        String accountPassword = request.getAccountPassword();
        Client client = clientService.findById(request.getCusNo());

        if (!StringUtils.hasText(accountNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "AccountService :: createAccount :: accountNumber is null"
            );
        }

        if (!StringUtils.hasText(accountPassword)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AccountService :: createAccount :: accountPassword is null"
            );
        }

        Account account = Account.builder()
                .id(accountNumber)
                .password(accountPassword)
                .client(client)
                .build();

        accountRepository.save(account);

        return AccountOpenResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }


    public AccountListQueryResponse getAllAccount(@Valid AccountListQueryRequest request) {
        Long cusNo = request.getCusNo();

        Client queryClient = clientService.findById(cusNo);

        List<Account> accounts = accountRepository.findByClient(queryClient);

        List<AccountNumber> accountNumbers = accounts.stream()
                .map(account -> {
                    String accountNumber = account.getId();

                    return AccountNumber.builder()
                            .accountNumber(accountNumber)
                            .build();
                })
                .collect(Collectors.toList());

        return AccountListQueryResponse.builder()
                .accounts(accountNumbers)
                .build();
    }

    public AccountBalanceQueryResponse getBalance(AccountBalanceQueryRequest request) {
        String accountNumber = request.getAccountNumber();

        Account queryAccount = accountRepository.findById(accountNumber)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "AccountService :: getBalance :: queryAccount does not exist"
                    );
                });

        Long balance = queryAccount.getBalance();

        return AccountBalanceQueryResponse.builder()
                .balance(balance)
                .build();
    }

    @Transactional
    public DepositResponse deposit(@Valid DepositRequest request) throws ResponseStatusException {
        Long amount = request.getAmount();
        AccountInfo accountTo = request.getAccountTo();
        String nameFrom = request.getNameOpponent();

        if (accountTo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AccountService :: deposit :: accountTo is null"
            );
        }

        if (!StringUtils.hasText(nameFrom)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AccountService :: deposit :: nameFrom is null"
            );
        }

        String accountNumberTo = accountTo.getAccountNumber();
        Account depositAccount = accountRepository.findById(accountNumberTo)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "AccountService :: deposit :: depositAccount does not exist"
                    );
                });

        Balance beforeBalance = new Balance(depositAccount.getBalance());
        Balance afterBalance = new Balance(depositAccount.getBalance() + amount);

        Long validAfterBalance = validationService.calculateBalance(afterBalance);

        depositAccount.setBalance(validAfterBalance);
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
    public WithdrawResponse withdraw(@Valid WithdrawRequest request) throws ResponseStatusException {
        Long amount = request.getAmount();
        AccountInfo accountFrom = request.getAccountFrom();
        String nameTo = request.getNameOpponent();

        if (accountFrom == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AccountService :: withdraw :: accountFrom is null"
            );
        }

        if (!StringUtils.hasText(nameTo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AccountService :: withdraw :: nameTo is null"
            );
        }

        String accountNumberFrom = accountFrom.getAccountNumber();
        Account withdrawAccount = accountRepository.findById(accountNumberFrom)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "AccountService :: withdraw :: withdrawAccount is null"
                    );
                });

        validationService.equalPassword(request.getAccountPassword(), withdrawAccount.getPassword());

        Balance beforeBalance = new Balance(withdrawAccount.getBalance());
        Balance afterBalance = new Balance(withdrawAccount.getBalance() - amount);

        Long validAfterBalance = validationService.calculateBalance(afterBalance);

        withdrawAccount.setBalance(validAfterBalance);
        accountRepository.save(withdrawAccount);

        transactionHistoryService.createWithdrawHistory(accountNumberFrom,
                accountNumberFrom,
                nameTo,
                amount);

        return WithdrawResponse.builder()
                .isSuccess(true)
                .build();
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

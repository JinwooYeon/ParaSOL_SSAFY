package com.parasol.core.service;

import com.parasol.core.api_model.AccountHistory;
import com.parasol.core.api_model.AccountInfo;
import com.parasol.core.api_model.AccountQueryRequest;
import com.parasol.core.eenum.TransactionType;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ValidationService validationService;

    public TransactionHistory createDepositHistory(String accountFrom, String accountTo, String nameFrom, Long amount) {
        Long time = System.currentTimeMillis();
        Optional<Account> account = accountRepository.findById(accountTo);

        // 입금 요청일 때 toAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(account.get())
                .date(time)
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .transactionAccount(accountFrom)
                .transactionOpponent(nameFrom)
                .build();

        return transactionHistoryRepository.save(transactionHistory);
    }

    public TransactionHistory createWithdrawHistory(String accountFrom, String accountTo, String nameTo, Long amount) {
        Long time = System.currentTimeMillis();
        Optional<Account> account = accountRepository.findById(accountTo);

        // 출금 요청일 때 fromAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(account.get())
                .date(time)
                .type(TransactionType.WITHDRAW)
                .amount(amount)
                .transactionAccount(accountTo)
                .transactionOpponent(nameTo)
                .build();

        return transactionHistoryRepository.save(transactionHistory);
    }

    public TransactionHistory createRemitHistory(String accountFrom, String accountTo, Long amount) {
        Long time = System.currentTimeMillis();
        Optional<Account> account = accountRepository.findById(accountTo);

        // 송금 요청일 때 toAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(account.get())
                .date(time)
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .transactionAccount(accountFrom)
                .build();

        return transactionHistoryRepository.save(transactionHistory);
    }

    public List<AccountHistory> getAccountHistory(AccountQueryRequest request) {
        List<AccountHistory> result = new ArrayList<>();
        Optional<Account> account = accountRepository.findById(request.getAccountNumber());

        if(account.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        //validationService.equalPassword(accountPassword, account.get().getPassword());

        for(TransactionHistory e : transactionHistoryRepository.findByAccount_Id(request.getAccountNumber())
                .stream().sorted(Comparator.comparing(TransactionHistory::getDate).reversed()).collect(Collectors.toList())){
            AccountHistory ele = new AccountHistory();
            AccountInfo accountInfo = new AccountInfo();

            accountInfo.setBankAccountNumber(e.getAccount().getId());

            ele.setTxId(e.getId());
            ele.setTxDatetime(e.getDate());
            ele.setTxMethod(e.getType());
            ele.setAmount(e.getAmount());
            ele.setAccountTo(accountInfo);
            ele.setTransactionAccount(e.getTransactionAccount());
            ele.setTransactionOpponent(e.getTransactionOpponent());


            result.add(ele);
        }

        return result;
    }
}

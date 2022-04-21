package com.parasol.core.service;

import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.eenum.TransactionType;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionHistory createDepositHistory(String accountFrom, String accountTo, Long amount) {
        Long time = System.currentTimeMillis();
        Account account = accountRepository.findByAccountNo(accountTo);

        // 입금 요청일 때 toAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(account)
                .transactionDate(time)
                .transactionType(TransactionType.DEPOSIT)
                .transactionAmount(amount)
                .transactionAccount(accountFrom)
                .build();

        return transactionHistoryRepository.save(transactionHistory);
    }

    public TransactionHistory createWithdrawHistory(String accountFrom, String accountTo, Long amount) {
        Long time = System.currentTimeMillis();
        Account account = accountRepository.findByAccountNo(accountFrom);

        // 츌굼 요청일 때 fromAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(account)
                .transactionDate(time)
                .transactionType(TransactionType.WITHDRAW)
                .transactionAmount(amount)
                .transactionAccount(accountTo)
                .build();

        return transactionHistoryRepository.save(transactionHistory);
    }

    public List<TransactionHistory> getAccountHistory(String accountNo){
        return transactionHistoryRepository.findByAccount_AccountNo(accountNo)
                .stream().sorted(Comparator.comparing(TransactionHistory::getTransactionDate).reversed()).collect(Collectors.toList());
    }
}

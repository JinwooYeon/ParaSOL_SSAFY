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

    public TransactionHistory createAccountHistory(AccountRequest request) {
        TransactionType type = request.getType();
        Account account = accountRepository.findByAccountNo(request.getAccountFrom().getBankAccountNumber());
        Long time = System.currentTimeMillis();

        if(type.equals(TransactionType.DEPOSIT)) {
            // 입금 요청일 때 fromAccount에 입금 거래내역 추가
            TransactionHistory transactionHistory = TransactionHistory.builder()
                    .account(account)
                    .transactionDate(time)
                    .transactionType(type)
                    .transactionAmount(request.getAmount())
                    .transactionAccount(request.getAccountTo().getBankAccountNumber())
                    .build();

        } else if(type.equals(TransactionType.WITHDRAW)) {
            // 출금 요청일 때 fromAccount에 출금 거래내역 추가
        }


        return null;
    }

    public List<TransactionHistory> getAccountHistory(String accountNo){
        return transactionHistoryRepository.findByAccount_AccountNo(accountNo)
                .stream().sorted(Comparator.comparing(TransactionHistory::getTransactionDate).reversed()).collect(Collectors.toList());
    }
}

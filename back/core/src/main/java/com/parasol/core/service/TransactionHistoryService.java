package com.parasol.core.service;

import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        return null;
    }

    public List<TransactionHistory> getAccountHistory(String accountNo){
        return transactionHistoryRepository.findByAccount_AccountNo(accountNo)
                .stream().sorted(Comparator.comparing(TransactionHistory::getTransactionDate).reversed()).collect(Collectors.toList());
    }
}

package com.parasol.core.service;

import com.parasol.core.api_model.AccountHistory;
import com.parasol.core.api_model.AccountHistoryQueryRequest;
import com.parasol.core.api_model.AccountHistoryQueryResponse;
import com.parasol.core.api_model.AccountInfo;
import com.parasol.core.eenum.TransactionType;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.repository.AccountRepositorySupport;
import com.parasol.core.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private AccountRepositorySupport accountRepositorySupport;

    public void createDepositHistory(String accountNumberFrom, String accountNumberTo, String nameFrom, Long amount) {
        if (!StringUtils.hasText(accountNumberFrom)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: createDepositHistory :: accountNumberFrom is null"
            );
        }

        if (!StringUtils.hasText(accountNumberTo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: createDepositHistory :: accountNumberTo is null"
            );
        }

        if (!StringUtils.hasText(nameFrom)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: createDepositHistory :: nameFrom is null"
            );
        }

        Long time = System.currentTimeMillis();
        Account accountTo = accountRepository.findById(accountNumberTo)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "TransactionHistoryService :: createDepositHistory :: accountTo does not exist"
                    );
                });

        // 입금 요청일 때 toAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(accountTo)
                .date(time)
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .nameOpponent(nameFrom)
                .build();

        transactionHistoryRepository.save(transactionHistory);
    }

    public void createWithdrawHistory(String accountNumberFrom, String accountNumberTo, String nameTo, Long amount) {
        if (!StringUtils.hasText(accountNumberFrom)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: createWithdrawHistory :: accountNumberFrom is null"
            );
        }

        if (!StringUtils.hasText(accountNumberTo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: createWithdrawHistory :: accountNumberTo is null"
            );
        }

        if (!StringUtils.hasText(nameTo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: createWithdrawHistory :: nameTo is null"
            );
        }

        Long time = System.currentTimeMillis();
        Account accountFrom = accountRepository.findById(accountNumberFrom)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "TransactionHistoryService :: createWithdrawHistory :: accountFrom does not exist"
                    );
                });

        // 출금 요청일 때 fromAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(accountFrom)
                .date(time)
                .type(TransactionType.WITHDRAW)
                .amount(amount)
                .nameOpponent(nameTo)
                .build();

        transactionHistoryRepository.save(transactionHistory);
    }

    public TransactionHistory createRemitHistory(String accountFrom, String accountTo, String nameTo, Long amount) {
        Optional<Account> account = accountRepository.findById(accountTo);
        Long time = System.currentTimeMillis();


        // 송금 요청일 때 toAccount에 입금 거래내역 추가. 받은 사람은 입금. 보낸 사람은 출금
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .account(account.get())
                .date(time)
                .type(TransactionType.DEPOSIT)
                .amount(amount)
                .build();

        return transactionHistoryRepository.save(transactionHistory);
    }
    
    public AccountHistoryQueryResponse getAccountHistory(AccountHistoryQueryRequest request) {
        Long cusNo = request.getCusNo();
        String accountNumber = request.getAccountNumber();

        if (cusNo == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "TransactionHistoryService :: withdraw :: this request does not authorized"
            );
        }

        if (!StringUtils.hasText(accountNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "TransactionHistoryService :: getAccountHistory :: accountNumber is null"
            );
        }

        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "TransactionHistoryService :: getAccountHistory :: account does not exist"
                    );
                });

        if (account.getClient().getId() != cusNo) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "TransactionHistoryService :: getAccountHistory :: this account is not yours"
            );
        }

        List<TransactionHistory> transactionHistories = accountRepositorySupport.getTransactionHistory(accountNumber);
        List<AccountHistory> accountHistories = transactionHistories.stream()
                .map(transactionHistory -> {
                    Long txId = transactionHistory.getId();
                    Long txDatetime = transactionHistory.getDate();
                    TransactionType txMethod = transactionHistory.getType();
                    Long txAmount = transactionHistory.getAmount();
                    String txAccount = transactionHistory.getAccount().getId();
                    String txNameOpponent = transactionHistory.getNameOpponent();

                    return AccountHistory.builder()
                            .id(txId)
                            .datetime(txDatetime)
                            .method(txMethod)
                            .amount(txAmount)
                            .account(txAccount)
                            .nameOpponent(txNameOpponent)
                            .build();
                })
                .collect(Collectors.toList());

        return AccountHistoryQueryResponse.builder()
                .accountHistories(accountHistories)
                .build();
    }
}

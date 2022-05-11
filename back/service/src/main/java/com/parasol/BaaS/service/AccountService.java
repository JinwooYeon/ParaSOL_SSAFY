package com.parasol.BaaS.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parasol.BaaS.api_model.AccountHistory;
import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.db.entity.BankConnection;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.BankConnectionRepository;
import com.parasol.BaaS.enums.TransactionType;
import com.parasol.BaaS.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountService {

    @Autowired
    BankConnectionRepository bankConnectionRepository;

    @Autowired
    BankLoginRequestFactory bankLoginRequestFactory;

    @Autowired
    QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;

    @Autowired
    QueryAccountListRequestFactory queryAccountListRequestFactory;

    @Autowired
    QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;

    @Autowired
    DepositRequestFactory depositRequestFactory;

    @Autowired
    WithdrawRequestFactory withdrawRequestFactory;

    public Mono<AccountBalanceQueryResultResponse> getBalance(User user, String bankName, String bankAccountNumber) {
        BankConnection bankConnection = getBankConnection(user, bankName);

        if (!bankName.equals("SBJ")) throw new IllegalArgumentException("We can support SBJ Bank only.");

        QueryAccountBalanceRequest queryRequest = QueryAccountBalanceRequest.builder()
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        return queryAccountBalanceRequestFactory.create(queryRequest)
                .filter(Objects::nonNull)
                .flatMap(balance -> Mono.just(
                        AccountBalanceQueryResultResponse.builder()
                                .bankName(bankName)
                                .bankAccountNumber(bankAccountNumber)
                                .totalBalance(balance.getTotalBalance())
                                .availableBalance(balance.getAvailableBalance())
                                .build()
                ));
    }

    public Mono<AccountListQueryResultResponse> getAccountList(User user, String bankName) {
        BankConnection bankConnection = getBankConnection(user, bankName);

        QueryAccountListRequest queryRequest = QueryAccountListRequest.builder()
                .bankName(bankName)
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        if (!bankName.equals("SBJ")) throw new IllegalArgumentException("We can support SBJ Bank only.");
        return queryAccountListRequestFactory.create(queryRequest)
                .filter(Objects::nonNull)
                .flatMap(rawBankAccounts -> {
                    List<AccountInfo> bankAccounts = toAccountInfoList(rawBankAccounts);

                    return Mono.just(
                            AccountListQueryResultResponse.builder()
                                .bankName(bankName)
                                .bankAccounts(bankAccounts)
                                .build()
                    );
                });
    }

    public Mono<AccountHistoryQueryResultResponse> getAccountHistory(User user, String bankName, String bankAccountNumber) {
        BankConnection bankConnection = getBankConnection(user, bankName);

        if (!bankName.equals("SBJ")) throw new IllegalArgumentException("We can support SBJ Bank only.");

        QueryAccountHistoryRequest queryRequest = QueryAccountHistoryRequest.builder()
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .id(bankConnection.getBankId())
                .password(bankConnection.getBankPassword())
                .build();

        return queryAccountHistoryRequestFactory.create(queryRequest)
                .filter(Objects::nonNull)
                .flatMap(bankAccountHistories -> {
                    return Mono.just(
                            AccountHistoryQueryResultResponse.builder()
                                    .bankName(bankName)
                                    .bankAccountNumber(bankAccountNumber)
                                    .bankAccountHistories(bankAccountHistories)
                                    .build()
                    );
                });
    }

    public TransactionExecuteResultResponse deposit(DepositRequest request) {
        TransactionType method = request.getMethod();
        Long amount = request.getAmount();
        AccountInfo accountFrom = request.getAccountFrom();
        AccountInfo accountTo = request.getAccountTo();

        try {
            TransactionExecuteResultResponse response = depositRequestFactory.create(request);
            return response;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public TransactionExecuteResultResponse withdraw(WithdrawRequest request) {
        TransactionType method = request.getMethod();
        Long amount = request.getAmount();
        AccountInfo accountFrom = request.getAccountFrom();
        AccountInfo accountTo = request.getAccountTo();

        try {
            TransactionExecuteResultResponse response = withdrawRequestFactory.create(request);
            return response;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<AccountInfo> toAccountInfoList(List<String> accounts) {
        return accounts
                .stream()
                .map(accountNumber -> AccountInfo.builder()
                        .accountNumber(accountNumber)
                        .build())
                .collect(Collectors.toList());
    }

    public BankConnection getBankConnection(User user, String bankName) throws IllegalStateException {
        return bankConnectionRepository
                .findByUser_UserSeqAndBankName(user.getUserSeq(), bankName)
                .orElseThrow(IllegalStateException::new);
    }
}

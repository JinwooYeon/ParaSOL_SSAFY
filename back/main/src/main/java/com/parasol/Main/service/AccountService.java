package com.parasol.Main.service;

import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.*;
import com.parasol.Main.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private OpenAccountRequestFactory openAccountRequestFactory;
    @Autowired
    private QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;
    @Autowired
    private QueryAccountListRequestFactory queryAccountListRequestFactory;
    @Autowired
    private QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;
    @Autowired
    private DepositRequestFactory depositRequestFactory;
    @Autowired
    private WithdrawRequestFactory withdrawRequestFactory;
    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

    public Mono<String> create(AccountOpenRequest request) {
        return openAccountRequestFactory.createOpenAccountRequest(request);
    }

    public Mono<AccountListQueryResultResponse> getAllAccount(AccountListQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .filter(LoginResult::getIsSuccess)
                .flatMap(
                        loginResult -> {
                            Long cusNo = loginResult.getCusNo();

                            AccountListQueryParam param = AccountListQueryParam.builder()
                                    .cusNo(cusNo)
                                    .build();

                            return queryAccountListRequestFactory.createQueryAccountListRequest(param)
                                    .map(queryResult ->
                                            AccountListQueryResultResponse.builder()
                                                    .accounts(queryResult.getAccounts())
                                                    .build()
                                    );
                        }
                );
    }

    public Mono<AccountBalanceQueryResultResponse> getBalance(AccountBalanceQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getBankId())
                .password(request.getBankPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .filter(LoginResult::getIsSuccess)
                .flatMap(
                        loginResult -> {
                            Long cusNo = loginResult.getCusNo();

                            AccountBalanceQueryParam param = AccountBalanceQueryParam.builder()
                                    .cusNo(cusNo)
                                    .accountNumber(request.getAccountNumber())
                                    .build();

                            return queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(param)
                                    .map(queryResult ->
                                            AccountBalanceQueryResultResponse.builder()
                                                    .balance(queryResult.getBalance())
                                                    .build()
                                    );
                        }
                );
    }

    public Mono<AccountHistoryResultResponse> getHistory(AccountHistoryQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getBankId())
                .password(request.getBankPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .filter(LoginResult::getIsSuccess)
                .flatMap(
                        loginResult -> {
                            Long cusNo = loginResult.getCusNo();

                            AccountHistoryQueryParam param = AccountHistoryQueryParam.builder()
                                    .cusNo(cusNo)
                                    .accountNumber(request.getAccountNumber())
                                    .build();

                            return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(param)
                                    .map(queryResult ->
                                            AccountHistoryResultResponse.builder()
                                                    .accountHistories(queryResult.getAccountHistories())
                                                    .build()
                                    );
                        }
                );
    }

    public Mono<TransactionExecutionResultResponse> deposit(DepositRequest request) {
        return depositRequestFactory.run(request);
    }

    public Mono<TransactionExecutionResultResponse> withdraw(WithdrawRequest request) {
        return withdrawRequestFactory.run(request);
    }
}

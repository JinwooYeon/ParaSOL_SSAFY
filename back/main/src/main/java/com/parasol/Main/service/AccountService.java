package com.parasol.Main.service;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.*;
import com.parasol.Main.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

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

    public Mono<String> create(AccountOpenRequest request) {
        return openAccountRequestFactory.createOpenAccountRequest(request);
    }

    public Mono<AccountListQueryResultResponse> getAllAccount(AccountListQueryRequest request) {
        Mono<LoginResultResponse> loginResponse = userLoginSocketRequestFactory.userLoginRequest(request);

        return loginResponse
                .filter(Objects::nonNull)
                .filter(LoginResultResponse::getIsSuccess)
                .flatMap(response -> {
                    request.setCusNo(response.getCusNo());

                    return queryAccountListRequestFactory.createQueryAccountListRequest(request)
                            .filter(Objects::nonNull)
                            .flatMap(rawAccountNumbers ->
                                    Mono.just(
                                            AccountListQueryResultResponse.builder()
                                                    .accounts(
                                                            rawAccountNumbers
                                                                    .stream()
                                                                    .map(accountNumber -> AccountInfo.builder().accountNumber(accountNumber).build())
                                                                    .collect(Collectors.toList())
                                                    )
                                                    .build()
                                    )
                            );
                });
    }

    public Mono<AccountBalanceQueryResultResponse> getBalance(AccountBalanceQueryRequest request) {
        Mono<LoginResultResponse> loginResponse = userLoginSocketRequestFactory.userLoginRequest(request);

        return loginResponse
                .filter(Objects::nonNull)
                .filter(LoginResultResponse::getIsSuccess)
                .flatMap(response -> {
                    request.setCusNo(response.getCusNo());

                    return queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(request)
                            .filter(Objects::nonNull)
                            .flatMap(rawAccountBalance ->
                                    Mono.just(
                                            AccountBalanceQueryResultResponse.builder()
                                                    .totalBalance(rawAccountBalance)
                                                    .availableBalance(rawAccountBalance)
                                                    .build()
                                    )
                            );
                });
    }

    public Mono<AccountHistoriesQueryResultResponse> getHistory(AccountHistoryQueryRequest request) {
        Mono<LoginResultResponse> loginResponse = userLoginSocketRequestFactory.userLoginRequest(request);

        return loginResponse
                .filter(Objects::nonNull)
                .filter(LoginResultResponse::getIsSuccess)
                .flatMap(response -> {
                    request.setCusNo(response.getCusNo());

                    return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(request)
                            .filter(Objects::nonNull)
                            .flatMap(rawAccountHistories ->
                                    Mono.just(
                                            AccountHistoriesQueryResultResponse.builder()
                                                    .accountHistories(rawAccountHistories)
                                                    .build()
                                    )
                            );
                });
    }

    public Mono<Boolean> deposit(DepositRequest request) {
        Mono<LoginResultResponse> loginResponse = userLoginSocketRequestFactory.userLoginRequest(request);

        return loginResponse
                .filter(Objects::nonNull)
                .filter(LoginResultResponse::getIsSuccess)
                .flatMap(response -> {
                    request.setCusNo(response.getCusNo());

                    return depositRequestFactory.run(request);
                });
    }

    public Mono<Boolean> withdraw(WithdrawRequest request) {
        Mono<LoginResultResponse> loginResponse = userLoginSocketRequestFactory.userLoginRequest(request);

        return loginResponse
                .filter(Objects::nonNull)
                .filter(LoginResultResponse::getIsSuccess)
                .flatMap(response -> {
                    request.setCusNo(response.getCusNo());

                    return withdrawRequestFactory.run(request);
                });
    }
}

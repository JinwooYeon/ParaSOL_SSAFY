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

    public Mono<List<AccountInfo>> getAllAccount(LoginRequest loginRequest) {
        Mono<LoginResultResponse> loginResponse = userLoginSocketRequestFactory.userLoginRequest(loginRequest);

        return loginResponse
                .filter(Objects::nonNull)
                .filter(LoginResultResponse::getIsSuccess)
                .flatMap(response -> {
                    AccountListQueryRequest queryRequest = AccountListQueryRequest.builder()
                            //.id(임의의 UUID)를 통해 테스트 가능
                            // 현재 로그인 로직 미비로 인해 오작동
                            .id(response.getCusNo())
                            .build();

                    return queryAccountListRequestFactory.createQueryAccountListRequest(queryRequest)
                            .filter(Objects::nonNull)
                            .flatMap(rawAccountNumbers ->
                                    Mono.just(rawAccountNumbers
                                            .stream()
                                            .map(accountNumber -> AccountInfo.builder().accountNumber(accountNumber).build())
                                            .collect(Collectors.toList())
                                    )
                            );
                });
    }

    public Mono<AccountBalanceQueryResultResponse> getBalance(AccountBalanceQueryRequest request) {
        return queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(request)
                .filter(Objects::nonNull)
                .flatMap(balance ->
                    Mono.just(
                            AccountBalanceQueryResultResponse.builder()
                                .totalBalance(balance)
                                .availableBalance(balance)
                                .build()
                    )
                );
    }

    public Mono<List<AccountHistory>> getHistory(AccountHistoryQueryRequest request) {
        return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(request);
    }

    public Mono<Boolean> deposit(DepositRequest request) {
        return depositRequestFactory.run(request);
    }

    public Mono<Boolean> withdraw(WithdrawRequest request) {
        return withdrawRequestFactory.run(request);
    }
}

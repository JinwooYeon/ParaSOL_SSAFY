package com.parasol.pay.service;

import com.parasol.pay.api_request.AccountBalanceQueryParam;
import com.parasol.pay.api_request.AccountBalanceQueryRequest;
import com.parasol.pay.api_request.LoginParam;
import com.parasol.pay.api_response.AccountBalanceQueryResponse;
import com.parasol.pay.api_response.LoginResult;
import com.parasol.pay.modules.QueryAccountBalanceRequestFactory;
import com.parasol.pay.modules.QueryAccountBalanceSocketRequestFactory;
import com.parasol.pay.modules.UserLoginSocketRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;
    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;
    @Autowired
    private QueryAccountBalanceSocketRequestFactory queryAccountBalanceSocketRequestFactory;

    public Mono<AccountBalanceQueryResponse> getBalance(AccountBalanceQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .doOnError( (throwable) -> {
                    WebClientResponseException ex = (WebClientResponseException)throwable;

                    if (ex.getStatusCode().is4xxClientError())
                        throw new ResponseStatusException(ex.getStatusCode());
                    else if (ex.getStatusCode().is5xxServerError())
                        throw new ResponseStatusException(ex.getStatusCode());
                })
                .flatMap(
                        loginResult -> {
                            if (!loginResult.getIsSuccess())
                                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

                            Long cusNo = loginResult.getCusNo();

                            AccountBalanceQueryParam param = AccountBalanceQueryParam.builder()
                                    .cusNo(cusNo)
                                    .accountNumber(request.getAccountNumber())
                                    .build();

                            return queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(param)
                                    .doOnError( (throwable) -> {
                                        WebClientResponseException ex = (WebClientResponseException)throwable;

                                        if (ex.getStatusCode().is4xxClientError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                        else if (ex.getStatusCode().is5xxServerError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                    })
                                    .map(queryResult ->
                                            AccountBalanceQueryResponse.builder()
                                                    .balance(queryResult.getBalance())
                                                    .build()
                                    );
                        }
                );
    }
}

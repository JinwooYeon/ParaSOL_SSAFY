package com.parasol.accountHistory.service;

import com.parasol.accountHistory.api_request.*;
import com.parasol.accountHistory.api_response.*;
import com.parasol.accountHistory.eenum.TransactionType;
import com.parasol.accountHistory.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;
    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

    public Mono<AccountHistoryResultResponse> getHistory(AccountHistoryQueryRequest request) {
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

                            AccountHistoryQueryParam param = AccountHistoryQueryParam.builder()
                                    .cusNo(cusNo)
                                    .accountNumber(request.getAccountNumber())
                                    .build();

                            return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(param)
                                    .doOnError( (throwable) -> {
                                        WebClientResponseException ex = (WebClientResponseException)throwable;

                                        if (ex.getStatusCode().is4xxClientError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                        else if (ex.getStatusCode().is5xxServerError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                    })
                                    .map(queryResult ->
                                            AccountHistoryResultResponse.builder()
                                                    .accountHistories(queryResult.getAccountHistories())
                                                    .build()
                                    );
                        }
                );
    }

}

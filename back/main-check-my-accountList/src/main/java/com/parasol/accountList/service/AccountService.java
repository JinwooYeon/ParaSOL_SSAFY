package com.parasol.accountList.service;

import com.parasol.accountList.api_request.*;
import com.parasol.accountList.api_response.*;
import com.parasol.accountList.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private QueryAccountListRequestFactory queryAccountListRequestFactory;

    @Autowired
    private QueryAccountListSocketRequestFactory queryAccountListSocketRequestFactory;

    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

    public Mono<AccountListQueryResultResponse> getAllAccount(AccountListQueryRequest request) {
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

                            AccountListQueryParam param = AccountListQueryParam.builder()
                                    .cusNo(cusNo)
                                    .build();

                            return queryAccountListRequestFactory.createQueryAccountListRequest(param)
                                    .doOnError( (throwable) -> {
                                        WebClientResponseException ex = (WebClientResponseException)throwable;

                                        if (ex.getStatusCode().is4xxClientError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                        else if (ex.getStatusCode().is5xxServerError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                    })
                                    .map(queryResult ->
                                            AccountListQueryResultResponse.builder()
                                                    .accounts(queryResult.getAccounts())
                                                    .build()
                                    );
                        }
                );
    }

}

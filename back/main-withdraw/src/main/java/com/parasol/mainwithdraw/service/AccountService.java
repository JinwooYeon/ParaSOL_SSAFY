package com.parasol.mainwithdraw.service;

import com.parasol.mainwithdraw.api_request.LoginParam;
import com.parasol.mainwithdraw.api_request.WithdrawParam;
import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.LoginResult;
import com.parasol.mainwithdraw.api_response.WithdrawResponse;
import com.parasol.mainwithdraw.eenum.TransactionType;
import com.parasol.mainwithdraw.modules.UserLoginSocketRequestFactory;
import com.parasol.mainwithdraw.modules.WithdrawRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private WithdrawRequestFactory withdrawRequestFactory;

    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

    public Mono<WithdrawResponse> withdraw(WithdrawRequest request) {
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

                            WithdrawParam param = WithdrawParam.builder()
                                    .cusNo(cusNo)
                                    .method(TransactionType.DEPOSIT)
                                    .amount(request.getAmount())
                                    .accountFrom(request.getAccountFrom())
                                    .nameOpponent(request.getNameTo())
                                    .accountPassword(request.getAccountPassword())
                                    .build();

                            return withdrawRequestFactory.run(param)
                                    .doOnError( (throwable) -> {
                                        WebClientResponseException ex = (WebClientResponseException)throwable;

                                        if (ex.getStatusCode().is4xxClientError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                        else if (ex.getStatusCode().is5xxServerError())
                                            throw new ResponseStatusException(ex.getStatusCode());
                                    })
                                    .map(queryResult ->
                                            WithdrawResponse.builder()
                                                    .isSuccess(queryResult.isSuccess())
                                                    .build()
                                    );
                        }
                );

    }
}

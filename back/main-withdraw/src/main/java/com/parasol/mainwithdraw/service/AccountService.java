package com.parasol.mainwithdraw.service;

import com.parasol.mainwithdraw.api_request.WithdrawParam;
import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.WithdrawResponse;
import com.parasol.mainwithdraw.eenum.TransactionType;
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


    public Mono<WithdrawResponse> withdraw(WithdrawRequest request) {
        WithdrawParam param = WithdrawParam.builder()
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
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                    else if (ex.getStatusCode().is5xxServerError())
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                })
                .map(queryResult ->
                        WithdrawResponse.builder()
                                .isSuccess(queryResult.isSuccess())
                                .build()
                );

    }
}

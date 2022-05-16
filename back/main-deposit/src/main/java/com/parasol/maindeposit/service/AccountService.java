package com.parasol.maindeposit.service;

import com.parasol.maindeposit.api_request.DepositParam;
import com.parasol.maindeposit.api_request.DepositRequest;
import com.parasol.maindeposit.api_response.DepositResponse;
import com.parasol.maindeposit.eenum.TransactionType;
import com.parasol.maindeposit.modules.DepositRequestFactory;
import com.parasol.maindeposit.modules.DepositSocketRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private DepositRequestFactory depositRequestFactory;

    @Autowired
    private DepositSocketRequestFactory depositSocketRequestFactory;

    public Mono<DepositResponse> deposit(DepositRequest request) {
        DepositParam param = DepositParam.builder()
                .method(TransactionType.DEPOSIT)
                .amount(request.getAmount())
                .accountTo(request.getAccountTo())
                .nameOpponent(request.getNameFrom())
                .build();

        return depositRequestFactory.run(param)
                .doOnError( (throwable) -> {
                    WebClientResponseException ex = (WebClientResponseException)throwable;

                    if (ex.getStatusCode().is4xxClientError())
                        throw new ResponseStatusException(ex.getStatusCode());
                    else if (ex.getStatusCode().is5xxServerError())
                        throw new ResponseStatusException(ex.getStatusCode());
                })
                .map(queryResult ->
                        DepositResponse.builder()
                                .isSuccess(queryResult.getIsSuccess())
                                .build()
                );

    }
}

package com.parasol.maindeposit.service;

import com.parasol.maindeposit.api_request.DepositParam;
import com.parasol.maindeposit.api_request.DepositRequest;
import com.parasol.maindeposit.api_response.DepositResponse;
import com.parasol.maindeposit.eenum.TransactionType;
import com.parasol.maindeposit.modules.DepositRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private DepositRequestFactory depositRequestFactory;

    public Mono<DepositResponse> deposit(DepositRequest request) {
        DepositParam param = DepositParam.builder()
                .method(TransactionType.DEPOSIT)
                .amount(request.getAmount())
                .accountTo(request.getAccountTo())
                .nameOpponent(request.getNameFrom())
                .build();

        return depositRequestFactory.run(param)
                .map(queryResult ->
                        DepositResponse.builder()
                                .isSuccess(queryResult.isSuccess())
                                .build()
                );

    }
}

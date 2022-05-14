package com.parasol.mainwithdraw.service;

import com.parasol.mainwithdraw.api_request.WithdrawParam;
import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.WithdrawResponse;
import com.parasol.mainwithdraw.eenum.TransactionType;
import com.parasol.mainwithdraw.modules.WithdrawRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                .map(queryResult ->
                        WithdrawResponse.builder()
                                .isSuccess(queryResult.isSuccess())
                                .build()
                );

    }
}

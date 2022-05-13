package com.parasol.mainwithdraw.service;

import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.TransactionExecutionResultResponse;
import com.parasol.mainwithdraw.modules.WithdrawRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private WithdrawRequestFactory withdrawRequestFactory;


    public Mono<TransactionExecutionResultResponse> withdraw(WithdrawRequest request) {
        return withdrawRequestFactory.run(request);
    }
}

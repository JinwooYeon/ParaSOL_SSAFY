package com.parasol.pay.controller;

import com.parasol.pay.api_request.AccountBalanceQueryRequest;
import com.parasol.pay.api_response.AccountBalanceQueryResponse;
import com.parasol.pay.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    // 계좌 잔액 조회
    @PostMapping("account/balance")
    @ResponseBody
    public Mono<AccountBalanceQueryResponse> getBalance(
            @RequestBody @Valid AccountBalanceQueryRequest request
    ) {
        return accountService.getBalance(request);
    }

}

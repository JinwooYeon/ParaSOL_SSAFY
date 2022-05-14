package com.parasol.mainwithdraw.controller;

import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.WithdrawResponse;
import com.parasol.mainwithdraw.service.AccountService;
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

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public Mono<WithdrawResponse> withdraw(
            @RequestBody @Valid WithdrawRequest withdrawRequest
    ) {
        return accountService.withdraw(withdrawRequest);
    }

}

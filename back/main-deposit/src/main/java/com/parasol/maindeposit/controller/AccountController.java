package com.parasol.maindeposit.controller;

import com.parasol.maindeposit.api_request.DepositRequest;
import com.parasol.maindeposit.api_response.DepositResponse;
import com.parasol.maindeposit.service.AccountService;
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

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public Mono<DepositResponse> deposit(
            @RequestBody @Valid DepositRequest request
    ) {
        return accountService.deposit(request);
    }

}

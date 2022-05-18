package com.parasol.accountList.controller;

import com.parasol.accountList.api_request.*;
import com.parasol.accountList.api_response.*;
import com.parasol.accountList.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    // 계좌 목록 조회
    @PostMapping("account/list")
    @ResponseBody
    public Mono<AccountListQueryResultResponse> getList(
            @RequestBody @Valid AccountListQueryRequest request
    ) {
        return accountService.getAllAccount(request);
    }

}

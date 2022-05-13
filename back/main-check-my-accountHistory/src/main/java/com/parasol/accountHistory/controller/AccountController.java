package com.parasol.accountHistory.controller;

import com.parasol.accountHistory.api_request.*;
import com.parasol.accountHistory.api_response.*;
import com.parasol.accountHistory.service.AccountService;
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

    // 계좌 거래내역 조회
    @PostMapping("account/history")
    @ResponseBody
    public Mono<AccountHistoryResultResponse> getHistory(
            @RequestBody @Valid AccountHistoryQueryRequest request
    ) {
        return accountService.getHistory(request);
    }


}

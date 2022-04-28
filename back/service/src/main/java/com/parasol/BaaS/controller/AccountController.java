package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_request.QueryAccountBalanceRequest;
import com.parasol.BaaS.api_response.AccountBalanceQueryResultResponse;
import com.parasol.BaaS.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account/balance")
    @ResponseBody
    public AccountBalanceQueryResultResponse getBalance(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String bankAccountNumber
    ) {
        QueryAccountBalanceRequest request = new QueryAccountBalanceRequest();
        request.setBankName(bankName);
        request.setBankAccountNumber(bankAccountNumber);

        AccountBalanceQueryResultResponse result = accountService.getBalance(request);
        return result;
    }
}

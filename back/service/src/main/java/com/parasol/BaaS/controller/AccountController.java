package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("balance")
    @ResponseBody
    public Mono<ResponseEntity<QueryAccountBalanceResponse>> getBalance(
            Authentication authentication,
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String bankAccountNumber
    ) {
        QueryAccountBalanceRequest request = QueryAccountBalanceRequest.builder()
                .authentication(authentication)
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .build();

        return accountService.getBalance(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping
    @ResponseBody
    public Mono<ResponseEntity<QueryAccountListResponse>> getAccountList(
            Authentication authentication,
            @RequestParam("bankName") String bankName
    ) {
        QueryAccountListRequest request = QueryAccountListRequest.builder()
                .authentication(authentication)
                .bankName(bankName)
                .build();

        return accountService.getAccountList(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("history")
    @ResponseBody
    public Mono<ResponseEntity<QueryAccountHistoryResponse>> getAccountHistory(
            Authentication authentication,
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String bankAccountNumber
    ) {
        QueryAccountHistoryRequest request = QueryAccountHistoryRequest.builder()
                .authentication(authentication)
                .bankName(bankName)
                .bankAccountNumber(bankAccountNumber)
                .build();

        return accountService.getAccountHistory(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("deposit")
    @ResponseBody
    public Mono<ResponseEntity<DepositResponse>> deposit(
            Authentication authentication,
            @RequestBody DepositRequest request
    ) {
        return accountService.deposit(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("withdraw")
    @ResponseBody
    public Mono<ResponseEntity<WithdrawResponse>> withdraw(
            @RequestBody WithdrawRequest request
    ) {
        return accountService.withdraw(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}

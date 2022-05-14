package com.parasol.core.controller;

import com.parasol.core.api_model.*;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionHistoryService transactionHistoryService;

    // 계좌 개설
    @PostMapping("account")
    @ResponseBody
    public ResponseEntity<AccountOpenResponse> createAccount(
            @RequestBody @Valid AccountOpenRequest accountOpenRequest
    ) {
        AccountOpenResponse response = accountService.createAccount(accountOpenRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 계좌 폐쇄
//    @DeleteMapping("account")
//    @ResponseBody
//    public String deleteAccount(
//            @RequestParam("accountNo") int accountNo,
//            @RequestParam("accountPassword") int accountPassword
//    ) {
//        return null;
//    }

    // 계좌 목록 조회
    @PostMapping("account/list")
    public ResponseEntity<AccountListQueryResponse> getAllAccount(
            @RequestBody @Valid AccountListQueryRequest request
    ) {
        AccountListQueryResponse response = accountService.getAllAccount(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 계좌 잔액 조회
    @PostMapping("account/balance")
    @ResponseBody
    public ResponseEntity<AccountBalanceQueryResponse> getBalance(
            @RequestBody @Valid AccountQueryBalanceRequest request
    ) {
        AccountBalanceQueryResponse response = accountService.getBalance(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 계좌 거래 내역 조회
    @PostMapping("account/history")
    @ResponseBody
    public ResponseEntity<AccountHistoryQueryResponse> getAccountHistory(
            @RequestBody @Valid AccountQueryRequest accountQueryRequest
    ) {
        AccountHistoryQueryResponse response = transactionHistoryService.getAccountHistory(accountQueryRequest.getAccountNumber());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public ResponseEntity<DepositResponse> deposit(
            @RequestBody @Valid DepositRequest request
    ) {
        DepositResponse response = accountService.deposit(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public ResponseEntity<WithdrawResponse> withdraw(
            @RequestBody @Valid WithdrawRequest request
    ) {
        WithdrawResponse response = accountService.withdraw(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 송금, from 계좌에서 출금, to 계좌에 입금
    @PostMapping("account/remit")
    @ResponseBody
    public TransactionExecutionResultResponse remit(
            @RequestBody @Valid AccountRequest request
    ){
        return accountService.remit(request);
    }
}

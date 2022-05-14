package com.parasol.core.controller;

import com.parasol.core.api_model.*;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createAccount(
            @RequestBody @Valid AccountOpenRequest accountOpenRequest
    ) {
        return accountService.Create(accountOpenRequest);
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
    public AccountListQueryResultResponse getAllAccount(
            @RequestBody @Valid AccountListQueryRequest request
    ) {
        return accountService.getAllAccount(request);
    }

    // 계좌 잔액 조회
    @PostMapping("account/balance")
    @ResponseBody
    public AccountBalanceQueryResponse getBalance(
            @RequestBody @Valid AccountQueryBalanceRequest request
    ) {
        return accountService.getBalance(request);
    }

    // 계좌 거래 내역 조회
    @PostMapping("account/history")
    @ResponseBody
    public AccountHistoryResultResponse getAccountHistory(
            @RequestBody @Valid AccountQueryRequest accountQueryRequest
    ) {
        return transactionHistoryService.getAccountHistory(accountQueryRequest.getAccountNumber());
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public DepositResponse deposit(
            @RequestBody @Valid DepositRequest request
    ) {
        return accountService.deposit(request);
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public WithdrawResponse withdraw(
            @RequestBody @Valid WithdrawRequest request
    ) {
        return accountService.withdraw(request);
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

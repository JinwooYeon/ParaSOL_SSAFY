package com.parasol.core.controller;

import com.parasol.core.api_model.*;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.ClientService;
import com.parasol.core.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<String> getAllAccount(
            @RequestBody @Valid AccountListQueryRequest request
    ) {
        return accountService.getAllAccount(request);
    }

    // 계좌 잔액 조회
    @PostMapping("account/balance")
    @ResponseBody
    public Long getBalance(
            @RequestBody @Valid AccountQueryRequest accountQueryRequest
    ) {
        return accountService.getBalanceWithPassword(accountQueryRequest);
    }

    // 계좌 거래 내역 조회
    @PostMapping("account/history")
    @ResponseBody
    public List<AccountHistory> getAccountHistory(
            @RequestBody @Valid AccountQueryRequest accountQueryRequest
    ) {
        return transactionHistoryService.getAccountHistory(accountQueryRequest.getAccountNumber(), accountQueryRequest.getAccountPassword());
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public TransactionExecutionResultResponse deposit(
            @RequestBody @Valid AccountRequest request
    ) {
        return accountService.deposit(request);
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public TransactionExecutionResultResponse withdraw(
            @RequestBody @Valid AccountWithdrawRequest request
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

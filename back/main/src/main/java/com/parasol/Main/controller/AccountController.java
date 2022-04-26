package com.parasol.Main.controller;

import com.parasol.Main.api_model.AccountBalance;
import com.parasol.Main.api_model.AccountRequest;
import com.parasol.Main.service.AccountService;
import com.parasol.Main.service.ClientService;
import com.parasol.Main.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    // TODO : 일단 오류 나는 거 다 주석처리 했습니다
    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    // 계좌 개설
    @PostMapping("account")
    @ResponseBody
    public String createAccount(
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountPassword") int accountPassword
    ) {

        return null;
    }

    // 계좌 목록 조회
    @GetMapping("account")
    @ResponseBody
    public AccountBalance getBalance(
            @RequestParam("name") String name
    ) {
        return null;
    }

    // 계좌 잔액 조회
    @GetMapping("account/balance")
    @ResponseBody
    public AccountBalance getBalance(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String accountNo
    ) {
        return null;
    }

    // 계좌 거래내역 조회
    @GetMapping("account/history")
    @ResponseBody
    public AccountBalance getBalance(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String accountNo
    ) {
        return null;
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    public boolean deposit(
            @RequestBody AccountRequest request
    ) {
        return false;
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public boolean withdraw(
            @RequestBody AccountRequest request
    ) {

        return false;
    }

}

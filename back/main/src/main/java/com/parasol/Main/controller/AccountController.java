package com.parasol.Main.controller;

import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.api_request.WithdrawRequest;
import com.parasol.Main.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    // TODO : 일단 오류 나는 거 다 주석처리 했습니다
    @Autowired
    private AccountService accountService;

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
    public String getList(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        return null;
    }

    // 계좌 잔액 조회
    @GetMapping("account/balance")
    @ResponseBody
    public String getBalance(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String accountNo
    ) {
        return null;
    }

    // 계좌 거래내역 조회
    @GetMapping("account/history")
    @ResponseBody
    public String getHistory(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String accountNo
    ) {
        return null;
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public String deposit(
            @RequestBody DepositRequest request
    ) {
        return null;
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public String withdraw(
            @RequestBody WithdrawRequest request
    ) {

        return null;
    }

}

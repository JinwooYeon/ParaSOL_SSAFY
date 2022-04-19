package com.parasol.core.controller;

import com.parasol.core.entity.Account;
import com.parasol.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("account")
    @ResponseBody
    public String CreateAccount(
            @RequestParam("clientSeq") int clientSeq,
            @RequestParam("accountPassword") int accountPassword
    ) {
        Account account = new Account();
        account.setAccountPassword(accountPassword);

        String result = accountService.Create(account);

        return result;
    }

    @DeleteMapping("account")
    @ResponseBody
    public String DeleteAccount(
            @RequestParam("accountNo") int accountNo,
            @RequestParam("accountPassword") int accountPassword
    ) {
        // TODO: 계좌 폐쇄

        return null;
    }

    @GetMapping("account")
    @ResponseBody
    public String GetAllAccount(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        // TODO: 계좌 목록 조회

        return name;
    }

    @GetMapping("account/balance")
    @ResponseBody
    public String GetAccountBalance(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountNo") String accountNo
    ) {
        // TODO: 계좌 잔액 조회

        return name;
    }

    @GetMapping("account/history")
    @ResponseBody
    public String GetAccountHistory(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountNo") String accountNo
    ) {
        // TODO: 계좌 거래내역 조회

        return name;
    }

    @PostMapping("account/deposit")
    @ResponseBody
    public String Deposit(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountNo") String accountNo
    ) {
        // TODO: 계좌 입금

        return name;
    }

    @PostMapping("account/withdraw")
    @ResponseBody
    public String Withdraw(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountNo") String accountNo
    ) {
        // TODO: 계좌 출금

        return name;
    }
}

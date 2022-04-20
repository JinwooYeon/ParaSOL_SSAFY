package com.parasol.core.controller;

import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @PostMapping("account")
    @ResponseBody
    public String createAccount(
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
    public String deleteAccount(
            @RequestParam("accountNo") int accountNo,
            @RequestParam("accountPassword") int accountPassword
    ) {
        // TODO: 계좌 폐쇄

        return null;
    }

    // 계좌 목록 조회
    @GetMapping("account")
    @ResponseBody
    public String getAllAccount(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        List<Account> result = accountService.findByClient(name, residentNumber);

        return "";
    }

    // 계좌 잔액 조회
    @GetMapping("account/balance")
    @ResponseBody
    public String getAccountBalance(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountNo") String accountNo
    ) {


        return name;
    }

    // 계좌 거래 내역 조회
    @GetMapping("account/history")
    @ResponseBody
    public List<TransactionHistory> getAccountHistory(
            @RequestParam("accountNo") String accountNo
    ) {
        // TODO: 계좌 거래내역 조회
        return transactionHistoryService.getAccountHistory(accountNo);
    }

    @PostMapping("account/deposit")
    @ResponseBody
    public boolean deposit(
            @RequestBody AccountRequest request
    ) {
        // TODO: 계좌 입금.....? 페이 충전. 내 계좌에서 페이 계좌로 송금

        /*
        * TODO : 유효 계좌인지 확인
        * */
        String accountFrom = request.getAccountFrom().getBankAccountNumber();

        boolean deposit = accountService.deposit(request);
        TransactionHistory transaction = transactionHistoryService.createAccountHistory(request);
        if(deposit && !transaction.equals(null)) return true;

        return false;
    }

    @PostMapping("account/withdraw")
    @ResponseBody
    public boolean withdraw(
            @RequestBody AccountRequest request
    ) {
        // TODO: 계좌 출금 ???  페이 사용

        boolean withdraw = accountService.deposit(request);
        TransactionHistory transaction = transactionHistoryService.createAccountHistory(request);
        if(withdraw && !transaction.equals(null)) return true;

        return false;
    }
}

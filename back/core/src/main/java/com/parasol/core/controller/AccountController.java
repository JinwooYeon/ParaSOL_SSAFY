package com.parasol.core.controller;

import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.Client;
import com.parasol.core.entity.TransactionHistory;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.ClientService;
import com.parasol.core.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

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
    public List<Account> getAllAccount(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        Client client = clientService.findByNameAndResidentNumber(name, residentNumber);
        List<Account> result = accountService.getAllAccount(client);

        return result;
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
        // 계좌 입금. to 계좌에 입금

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
        // 계좌 출금. from 계좌에서 출금
        boolean withdraw = accountService.withdraw(request);
        TransactionHistory transaction = transactionHistoryService.createAccountHistory(request);
        if(withdraw && !transaction.equals(null)) return true;

        return false;
    }
}

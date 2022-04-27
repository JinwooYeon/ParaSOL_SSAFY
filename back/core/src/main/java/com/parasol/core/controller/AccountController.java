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

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
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
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber,
            @RequestParam("accountPassword") int accountPassword
    ) {
        Client client = clientService.findByNameAndResidentNumber(name, residentNumber);

        Account account = new Account();
        account.setClient(client);
        account.setPassword(accountPassword);

        String result = accountService.Create(account);

        return result;
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
    public Long getBalance(
            @RequestParam("accountNo") String accountNo
    ) {
        return accountService.getBalance(accountNo);
    }

    // 계좌 거래 내역 조회
    @GetMapping("account/history")
    @ResponseBody
    public List<TransactionHistory> getAccountHistory(
            @RequestParam("accountNo") String accountNo
    ) {
        return transactionHistoryService.getAccountHistory(accountNo);
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public boolean deposit(
            @RequestBody @Valid AccountRequest request
    ) {

        /*
        * TODO : 유효 계좌인지 확인
        * */
        String accountFrom = request.getAccountFrom().getBankAccountNumber();
        String accountTo = request.getAccountTo().getBankAccountNumber();
        Long amount = request.getAmount();

        boolean deposit = accountService.deposit(request);
        TransactionHistory transaction = transactionHistoryService.createDepositHistory(accountFrom, accountTo, amount);
        if(deposit && !transaction.equals(null)) return true;

        return false;
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public boolean withdraw(
            @RequestBody @Valid AccountRequest request
    ) {
        String accountFrom = request.getAccountFrom().getBankAccountNumber();
        String accountTo = request.getAccountTo().getBankAccountNumber();
        Long amount = request.getAmount();

        boolean withdraw = accountService.withdraw(request);
        TransactionHistory transaction = transactionHistoryService.createWithdrawHistory(accountFrom, accountTo, amount);
        if(withdraw && !transaction.equals(null)) return true;

        return false;
    }

    // 송금, from 계좌에서 출금, to 계좌에 입금
    @PostMapping("account/remit")
    @ResponseBody
    public boolean remit(
            @RequestBody @Valid AccountRequest request
    ){
        String accountFrom = request.getAccountFrom().getBankAccountNumber();
        String accountTo = request.getAccountTo().getBankAccountNumber();
        Long amount = request.getAmount();

        // 송금하기 (출금 + 입금)
        boolean remit = accountService.remit(request);
        TransactionHistory transaction = transactionHistoryService.createRemitHistory(accountFrom, accountTo, amount);
        if(remit && !transaction.equals(null)) return true;

        return false;
    }
}

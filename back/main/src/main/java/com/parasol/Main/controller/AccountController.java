package com.parasol.Main.controller;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import com.parasol.Main.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    // 계좌 개설
    @PostMapping("account")
    @ResponseBody
    public Mono<String> createAccount(
            @RequestBody @Valid AccountOpenRequest accountOpenRequest
    ) {
        AccountOpenRequest request = new AccountOpenRequest();
        request.setName(accountOpenRequest.getName());
        request.setResidentNumber(accountOpenRequest.getResidentNumber());
        request.setAccountPassword(accountOpenRequest.getAccountPassword());

        Mono<String> result = accountService.create(request);
        return result;
    }

    // 계좌 목록 조회
    @GetMapping("account")
    @ResponseBody
    public Mono<AccountListQueryResultResponse> getList(
            @RequestBody @Valid AccountListQueryRequest accountListQueryRequest
    ) {
        AccountListQueryRequest request = new AccountListQueryRequest();
        request.setName(accountListQueryRequest.getName());
        request.setResidentNumber(accountListQueryRequest.getResidentNumber());

        Mono<AccountListQueryResultResponse> result = accountService.getAllAccount(request);
        return result;
    }

    // 계좌 잔액 조회
    @GetMapping("account/balance")
    @ResponseBody
    public Mono<AccountBalanceQueryResultResponse> getBalance(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String bankAccountNumber
    ) {
        AccountBalanceQueryRequest request = new AccountBalanceQueryRequest();
        request.setBankName(bankName);
        request.setBankAccountNumber(bankAccountNumber);

        Mono<AccountBalanceQueryResultResponse> result = accountService.getBalance(request);
        return result;
    }

    // 계좌 거래내역 조회
    @GetMapping("account/history")
    @ResponseBody
    public Mono<List<AccountHistory>> getHistory(
            @RequestParam("bankName") String bankName,
            @RequestParam("bankAccountNumber") String bankAccountNumber
    ) {
        AccountHistoryQueryRequest request = new AccountHistoryQueryRequest();
        request.setBankName(bankName);
        request.setBankAccountNumber(bankAccountNumber);

        Mono<List<AccountHistory>> result = accountService.getHistory(request);
        return result;
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public Mono<Boolean> deposit(
            @RequestBody @Valid DepositRequest depositRequest
    ) {
        DepositRequest request = new DepositRequest();
        request.setAccountFrom(depositRequest.getAccountFrom());
        request.setNameTo(depositRequest.getNameTo());
        request.setAmount(depositRequest.getAmount());
        request.setMethod(depositRequest.getMethod());

        Mono<Boolean> result = accountService.deposit(request);

        return result;
    }

    // 계좌 출금. from 계좌에서 출금
    @PostMapping("account/withdraw")
    @ResponseBody
    public Mono<Boolean> withdraw(
            @RequestBody @Valid WithdrawRequest withdrawRequest
    ) {
        WithdrawRequest request = new WithdrawRequest();

        request.setAccountFrom(withdrawRequest.getAccountFrom());
        request.setNameTo(withdrawRequest.getNameTo());
        request.setAmount(withdrawRequest.getAmount());
        request.setMethod(withdrawRequest.getMethod());

        Mono<Boolean> result = accountService.withdraw(request);

        return result;
    }

}

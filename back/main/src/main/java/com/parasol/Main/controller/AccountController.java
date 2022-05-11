package com.parasol.Main.controller;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
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
        request.setId(accountOpenRequest.getId());
        request.setAccountPassword(accountOpenRequest.getAccountPassword());

        Mono<String> result = accountService.create(request);
        return result;
    }

    // 계좌 목록 조회
    @PostMapping("account/list")
    @ResponseBody
    public Mono<List<String>> getList(
            @RequestBody @Valid AccountListQueryRequest accountListQueryRequest
    ) {
        AccountListQueryRequest request = new AccountListQueryRequest();
        request.setId(accountListQueryRequest.getId());

        return accountService.getAllAccount(request);
    }

    // 계좌 잔액 조회
    @PostMapping("account/balance")
    @ResponseBody
    public Mono<AccountBalanceQueryResultResponse> getBalance(
            @RequestBody @Valid AccountBalanceQueryRequest request
    ) {
        return accountService.getBalance(request);
    }

    // 계좌 거래내역 조회
    @PostMapping("account/history")
    @ResponseBody
    public Mono<List<AccountHistory>> getHistory(
            @RequestBody @Valid AccountHistoryQueryRequest request
    ) {
        return accountService.getHistory(request);
    }

    // 계좌 입금. to 계좌에 입금
    @PostMapping("account/deposit")
    @ResponseBody
    public Mono<Boolean> deposit(
            @RequestBody @Valid DepositRequest depositRequest
    ) {
        DepositRequest request = new DepositRequest();
        request.setAccountFrom(depositRequest.getAccountFrom());
        request.setAccountTo(depositRequest.getAccountTo());
        request.setAmount(depositRequest.getAmount());
        request.setMethod(depositRequest.getMethod());
        request.setNameOpponent(depositRequest.getNameOpponent());

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
        request.setAccountTo(withdrawRequest.getAccountTo());
        request.setAmount(withdrawRequest.getAmount());
        request.setMethod(withdrawRequest.getMethod());
        request.setNameOpponent(withdrawRequest.getNameOpponent());

        Mono<Boolean> result = accountService.withdraw(request);

        return result;
    }

}

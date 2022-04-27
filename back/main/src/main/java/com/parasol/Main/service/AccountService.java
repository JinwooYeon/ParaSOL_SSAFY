package com.parasol.Main.service;

import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountFixed accountFixed;

    public void create(AccountOpenRequest request) {
        String resident = request.getResidentNumber();
        int password = request.getAccountPassword();
        accountFixed.createOpenAccountRequest();
    }

    public AccountListQueryResultResponse getAllAccount(AccountListQueryRequest request) {
        accountFixed.createQueryAccountListRequest();
        return null;
    }

    public AccountBalanceQueryResultResponse getBalance(AccountBalanceQueryRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        accountFixed.createQueryAccountBalanceRequest();
        return null;
    }

    public AccountHistoriesQueryResultResponse getHistory(AccountHistoryQueryRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        accountFixed.createQueryAccountHistoryRequest();
        return null;
    }

    public TransactionExecuteResultResponse deposit(DepositRequest request) {
        accountFixed.createDepositRequest();
        return null;
    }

    public TransactionExecuteResultResponse withdraw(WithdrawRequest request) {
        accountFixed.createWithdrawRequest();
        return null;
    }
}

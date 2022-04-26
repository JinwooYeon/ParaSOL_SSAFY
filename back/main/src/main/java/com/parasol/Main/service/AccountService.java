package com.parasol.Main.service;

import com.parasol.Main.api_model.*;
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

    public AccountBalanceResponse getAllAccount(ClientInfoRequest request) {
        accountFixed.createQueryAccountListRequest();
        return null;
    }

    public AccountBalanceResponse getBalance(ClientAccountInfoRequest request) {
        String bankName = request.getAccountInfo().getBankName();
        String accountNo = request.getAccountInfo().getBankAccountNumber();

        accountFixed.createQueryAccountBalanceRequest();
        return null;
    }

    public AccountHistoriesResponse getHistory(ClientAccountInfoRequest request) {
        String bankName = request.getAccountInfo().getBankName();
        String accountNo = request.getAccountInfo().getBankAccountNumber();

        accountFixed.createQueryAccountHistoryRequest();
        return null;
    }

    public TransactionResponse deposit(TransactionRequest request) {
        accountFixed.createDepositRequest();
        return null;
    }

    public TransactionResponse withdraw(TransactionRequest request) {
        accountFixed.createWithdrawRequest();
        return null;
    }
}

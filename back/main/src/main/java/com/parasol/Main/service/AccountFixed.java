package com.parasol.Main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class AccountFixed {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    /*
    public AccountResponse createOpenAccountRequest(AccountOpenRequest accountInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountResponse createCloseAccountRequest(AccountCloseRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountResponse createQueryAccountListRequest(AccountResponse saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountResponse createQueryAccountBalanceRequest(AccountBalanceQueryRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountResponse createQueryAccountHistoryRequest(AccountHistoryQueryRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountResponse createDepositRequest(DepositRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountResponse createWithdrawRequest(WithdrawRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }
    */
}

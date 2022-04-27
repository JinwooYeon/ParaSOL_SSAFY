package com.parasol.Main.service;

import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class AccountFixed {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public Object createOpenAccountRequest(AccountOpenRequest accountInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public Object createCloseAccountRequest(AccountCloseRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountListQueryResultResponse createQueryAccountListRequest(AccountListQueryRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountBalanceQueryResultResponse createQueryAccountBalanceRequest(AccountBalanceQueryRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public AccountHistoriesQueryResultResponse createQueryAccountHistoryRequest(AccountHistoryQueryRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public TransactionExecuteResultResponse createDepositRequest(DepositRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public TransactionExecuteResultResponse createWithdrawRequest(WithdrawRequest saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }
}

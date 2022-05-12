package com.parasol.Main.service;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private OpenAccountRequestFactory openAccountRequestFactory;
    @Autowired
    private QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;
    @Autowired
    private QueryAccountListRequestFactory queryAccountListRequestFactory;
    @Autowired
    private QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;
    @Autowired
    private DepositRequestFactory depositRequestFactory;
    @Autowired
    private WithdrawRequestFactory withdrawRequestFactory;

    public Mono<String> create(AccountOpenRequest request) {
        return openAccountRequestFactory.createOpenAccountRequest(request);
    }

    public Mono<List<String>> getAllAccount(AccountListQueryRequest request) {
        return queryAccountListRequestFactory.createQueryAccountListRequest(request);
    }

    public Mono<AccountBalanceQueryResultResponse> getBalance(AccountBalanceQueryRequest request) {

        return queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(request);
    }

    public Mono<List<AccountHistory>> getHistory(AccountHistoryQueryRequest request) {
        return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(request);
    }

    public Mono<AccountBalanceQueryResultResponse> deposit(DepositRequest request) {
        return depositRequestFactory.run(request);
    }

    public Mono<AccountBalanceQueryResultResponse> withdraw(WithdrawRequest request) {
        return withdrawRequestFactory.run(request);
    }
}

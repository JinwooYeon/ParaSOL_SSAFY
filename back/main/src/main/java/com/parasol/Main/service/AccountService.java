package com.parasol.Main.service;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import com.parasol.Main.eenum.TransactionType;
import com.parasol.Main.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private OpenAccountRequestFactory openAccountRequestFactory;
//    @Autowired
//    private CloseAccountRequestFactory closeAccountRequestFactory;
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
        String resident = request.getResidentNumber();
        int password = request.getAccountPassword();
        String name = request.getName();
        return openAccountRequestFactory.createOpenAccountRequest(request);
    }

    public Mono<AccountListQueryResultResponse> getAllAccount(AccountListQueryRequest request) {
        String name = request.getName();
        String residentNumber = request.getResidentNumber();

        return queryAccountListRequestFactory.createQueryAccountListRequest(request);
    }

    public Mono<AccountBalanceQueryResultResponse> getBalance(AccountBalanceQueryRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        return queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(request);
    }

    public Mono<List<AccountHistory>> getHistory(AccountHistoryQueryRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(request);
    }

    public Mono<Boolean> deposit(DepositRequest request) {
        // TODO: 메소드명 수정 필요 @선민기
        //depositRequestFactory.createDepositRequest(request);
        AccountInfo accountFrom = request.getAccountFrom();
        AccountInfo accountTo = request.getAccountTo();
        long amount = request.getAmount();
        TransactionType method = request.getMethod();

        return depositRequestFactory.run(request);
    }

    public Mono<Boolean> withdraw(WithdrawRequest request) {
        // TODO: 구현 수정 필요 @선민기
        //withdrawRequestFactory.createWithdrawRequest(request);

        AccountInfo accountFrom = request.getAccountFrom();
        AccountInfo accountTo = request.getAccountTo();
        long amount = request.getAmount();
        TransactionType method = request.getMethod();

        return withdrawRequestFactory.run(request);
    }
}

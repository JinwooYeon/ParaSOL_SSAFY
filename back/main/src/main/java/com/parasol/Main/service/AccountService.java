package com.parasol.Main.service;

import com.parasol.Main.api_request.*;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import com.parasol.Main.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void create(AccountOpenRequest request) {
        String resident = request.getResidentNumber();
        int password = request.getAccountPassword();
        openAccountRequestFactory.createOpenAccountRequest(request);
    }

    public AccountListQueryResultResponse getAllAccount(AccountListQueryRequest request) {
        queryAccountListRequestFactory.createQueryAccountListRequest(request);
        return null;
    }

    public AccountBalanceQueryResultResponse getBalance(AccountBalanceQueryRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(request);
        return null;
    }

    public AccountHistoriesQueryResultResponse getHistory(AccountHistoryQueryRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(request);
        return null;
    }

    public TransactionExecuteResultResponse deposit(DepositRequest request) {
        // TODO: 메소드명 수정 필요 @선민기
        //depositRequestFactory.createDepositRequest(request);
        return null;
    }

    public TransactionExecuteResultResponse withdraw(WithdrawRequest request) {
        // TODO: 구현 수정 필요 @선민기
        //withdrawRequestFactory.createWithdrawRequest(request);
        return null;
    }
}

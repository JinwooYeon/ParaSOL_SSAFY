package com.parasol.BaaS.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parasol.BaaS.api_request.QueryAccountBalanceRequest;
import com.parasol.BaaS.api_request.QueryAccountListRequest;
import com.parasol.BaaS.api_response.AccountBalanceQueryResultResponse;
import com.parasol.BaaS.api_response.AccountListQueryResultResponse;
import com.parasol.BaaS.modules.QueryAccountBalanceRequestFactory;
import com.parasol.BaaS.modules.QueryAccountListRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;

    @Autowired
    QueryAccountListRequestFactory queryAccountListRequestFactory;

    public AccountBalanceQueryResultResponse getBalance(QueryAccountBalanceRequest request) {
        String bankName = request.getBankName();
        String accountNo = request.getBankAccountNumber();

        try {
            if (!bankName.equals("SBJ")) throw new IllegalArgumentException("We can support SBJ Bank only.");

            AccountBalanceQueryResultResponse response = queryAccountBalanceRequestFactory.create(request);
            return response;
        } catch (JsonProcessingException e) {
            System.out.println(e.toString());
            return null;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public AccountListQueryResultResponse getAccountList(QueryAccountListRequest request) {
        String bankName = request.getBankName();

        // TODO: DB에서 사전에 등록된 현재 사용자의 인터넷 뱅킹 계정을 얻어와야 함
        //String accountId = request.getId();
        //String accountPassword = request.getPassword();

        try {
            if (!bankName.equals("SBJ")) throw new IllegalArgumentException("We can support SBJ Bank only.");

            AccountListQueryResultResponse response = queryAccountListRequestFactory.create(request);
            return response;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

}

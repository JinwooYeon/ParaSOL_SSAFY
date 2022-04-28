package com.parasol.BaaS.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parasol.BaaS.api_request.QueryAccountBalanceRequest;
import com.parasol.BaaS.api_response.AccountBalanceQueryResultResponse;
import com.parasol.BaaS.modules.QueryAccountBalanceRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;

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

}

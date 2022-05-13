package com.parasol.accountHistory.service;

import com.parasol.accountHistory.api_request.*;
import com.parasol.accountHistory.api_response.*;
import com.parasol.accountHistory.eenum.TransactionType;
import com.parasol.accountHistory.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;
    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

    public Mono<AccountHistoryResultResponse> getHistory(AccountHistoryQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .filter(LoginResult::getIsSuccess)
                .flatMap(
                        loginResult -> {
                            Long cusNo = loginResult.getCusNo();

                            AccountHistoryQueryParam param = AccountHistoryQueryParam.builder()
                                    .cusNo(cusNo)
                                    .accountNumber(request.getAccountNumber())
                                    .build();

                            return queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(param)
                                    .map(queryResult ->
                                            AccountHistoryResultResponse.builder()
                                                    .accountHistories(queryResult.getAccountHistories())
                                                    .build()
                                    );
                        }
                );
    }

}

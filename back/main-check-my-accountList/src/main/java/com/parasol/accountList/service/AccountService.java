package com.parasol.accountList.service;

import com.parasol.accountList.api_request.*;
import com.parasol.accountList.api_response.*;
import com.parasol.accountList.modules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {

    @Autowired
    private QueryAccountListRequestFactory queryAccountListRequestFactory;

    @Autowired
    private QueryAccountListSocketRequestFactory queryAccountListSocketRequestFactory;

    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;

    public Mono<AccountListQueryResultResponse> getAllAccount(AccountListQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .filter(LoginResult::getIsSuccess)
                .flatMap(
                        loginResult -> {
                            Long cusNo = loginResult.getCusNo();

                            AccountListQueryParam param = AccountListQueryParam.builder()
                                    .cusNo(cusNo)
                                    .build();

                            return queryAccountListRequestFactory.createQueryAccountListRequest(param)
                                    .map(queryResult ->
                                            AccountListQueryResultResponse.builder()
                                                    .accounts(queryResult.getAccounts())
                                                    .build()
                                    );
                        }
                );
    }

}

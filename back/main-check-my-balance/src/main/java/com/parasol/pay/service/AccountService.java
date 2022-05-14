package com.parasol.pay.service;

import com.parasol.pay.api_request.AccountBalanceQueryParam;
import com.parasol.pay.api_request.AccountBalanceQueryRequest;
import com.parasol.pay.api_request.LoginParam;
import com.parasol.pay.api_response.AccountBalanceQueryResponse;
import com.parasol.pay.api_response.LoginResult;
import com.parasol.pay.modules.QueryAccountBalanceRequestFactory;
import com.parasol.pay.modules.QueryAccountBalanceSocketRequestFactory;
import com.parasol.pay.modules.UserLoginSocketRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;
    @Autowired
    private UserLoginSocketRequestFactory userLoginSocketRequestFactory;
    @Autowired
    private QueryAccountBalanceSocketRequestFactory queryAccountBalanceSocketRequestFactory;

    public Mono<AccountBalanceQueryResponse> getBalance(AccountBalanceQueryRequest request) {
        LoginParam loginParam = LoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return userLoginSocketRequestFactory.userLoginRequest(loginParam)
                .filter(LoginResult::getIsSuccess)
                .flatMap(
                        loginResult -> {
                            Long cusNo = loginResult.getCusNo();

                            AccountBalanceQueryParam param = AccountBalanceQueryParam.builder()
                                    .cusNo(cusNo)
                                    .accountNumber(request.getAccountNumber())
                                    .build();

                            return queryAccountBalanceSocketRequestFactory.createQueryAccountBalanceRequest(param)
                                    .map(queryResult ->
                                            AccountBalanceQueryResponse.builder()
                                                    .balance(queryResult.getBalance())
                                                    .build()
                                    );
                        }
                );
    }
}

package com.parasol.authentication.service;

import com.parasol.authentication.modules.UserLoginSocketRequestFactory;
import com.parasol.authentication.api_request.LoginParam;
import com.parasol.authentication.api_request.LoginRequest;
import com.parasol.authentication.api_response.LoginResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserLoginSocketRequestFactory userLoginRequestFactory;

    public Mono<LoginResultResponse> login(LoginRequest request) {
        LoginParam param = LoginParam.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();

        return userLoginRequestFactory.userLoginRequest(param)
                .map(result -> LoginResultResponse.builder()
                        .isSuccess(result.getIsSuccess())
                        .build()
                );
    }
}

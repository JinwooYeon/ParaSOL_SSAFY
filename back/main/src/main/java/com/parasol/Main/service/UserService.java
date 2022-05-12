package com.parasol.Main.service;

import com.parasol.Main.api_request.LoginParam;
import com.parasol.Main.api_request.LoginRequest;
import com.parasol.Main.api_response.LoginResult;
import com.parasol.Main.api_response.LoginResultResponse;
import com.parasol.Main.modules.UserLoginSocketRequestFactory;
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

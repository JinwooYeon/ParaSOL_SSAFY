package com.parasol.Main.service;

import com.parasol.Main.api_request.LoginRequest;
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
        return userLoginRequestFactory.userLoginRequest(request);
    }
}

package com.parasol.authentication.controller;

import com.parasol.authentication.api_response.LoginResultResponse;
import com.parasol.authentication.service.UserService;
import com.parasol.authentication.api_request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Mono<LoginResultResponse> login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

}

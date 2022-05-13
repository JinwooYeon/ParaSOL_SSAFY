package com.parasol.Main.controller;

import com.parasol.Main.api_request.LoginRequest;
import com.parasol.Main.api_response.LoginResponse;
import com.parasol.Main.service.UserService;
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
    public Mono<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return userService.login(request);
    }

}

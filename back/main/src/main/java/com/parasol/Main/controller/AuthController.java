package com.parasol.Main.controller;

import com.parasol.Main.api_request.RegisterRequest;
import com.parasol.Main.service.ApiKeyAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ApiKeyAuthenticateService apiKeyAuthenticateService;

    @PostMapping("register")
    public String register(@RequestBody(required = true) RegisterRequest registerRequest) {
        return apiKeyAuthenticateService.register(registerRequest);
    }
}

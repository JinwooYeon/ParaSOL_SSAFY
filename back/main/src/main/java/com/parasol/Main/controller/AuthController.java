package com.parasol.Main.controller;

import com.parasol.Main.service.ApiKeyAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ApiKeyAuthenticateService apiKeyAuthenticateService;

    @PostMapping
    public boolean register(@RequestBody(required = true) String apiKey) {
        return apiKeyAuthenticateService.register(apiKey);
    }

    @DeleteMapping
    public boolean revoke(@RequestBody(required = true) String apiKey) {
        return true;
    }
}

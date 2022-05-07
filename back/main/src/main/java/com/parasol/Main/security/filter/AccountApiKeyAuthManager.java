package com.parasol.Main.security.filter;

import com.parasol.Main.service.AuthenticateService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Primary
@Component("accountApiKeyAuthManager")
public class AccountApiKeyAuthManager extends ApiKeyAuthManager {

    public AccountApiKeyAuthManager() {
    }

    public AccountApiKeyAuthManager(AuthenticateService authenticateService, HttpServletRequest request) {
        super(authenticateService, request);
    }
}

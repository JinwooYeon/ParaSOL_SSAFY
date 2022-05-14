package com.parasol.main_authentication.security.filter;

import com.parasol.main_authentication.service.AuthenticateService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component("clientApiKeyAuthManager")
public class ClientApiKeyAuthManager extends ApiKeyAuthManager {

    public ClientApiKeyAuthManager() {

    }

    public ClientApiKeyAuthManager(AuthenticateService authenticateService, HttpServletRequest request) {
        super(authenticateService, request);
    }
}

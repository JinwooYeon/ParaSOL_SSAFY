package com.parasol.Main.security.filter;

import com.parasol.Main.service.AuthenticateService;
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

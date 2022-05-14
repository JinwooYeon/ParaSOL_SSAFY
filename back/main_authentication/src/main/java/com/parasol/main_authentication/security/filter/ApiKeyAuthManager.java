package com.parasol.main_authentication.security.filter;

import com.parasol.main_authentication.service.AuthenticateService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

public class ApiKeyAuthManager implements AuthenticationManager {
    private AuthenticateService service;
    private HttpServletRequest request;

    public ApiKeyAuthManager() {

    }

    public ApiKeyAuthManager(AuthenticateService service, HttpServletRequest request) {
        this.service = service;
        this.request = request;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String ipAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String path = request.getServletPath();

        if (!service.isValid(ipAddr, method, path, principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

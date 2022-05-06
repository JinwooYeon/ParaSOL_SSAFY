package com.parasol.Main.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;

public class ApiKeyAuthManager implements AuthenticationManager {
    private String TEST_KEY;
    private WebClient query;
    private HttpServletRequest request;

    public ApiKeyAuthManager() {

    }

    public ApiKeyAuthManager(String TEST_KEY, WebClient query, HttpServletRequest request) {
        this.TEST_KEY = TEST_KEY;
        this.query = query;
        this.request = request;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String ipAddr = request.getRemoteAddr();

        if (false) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

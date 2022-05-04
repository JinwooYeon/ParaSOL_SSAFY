package com.parasol.Main.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.reactive.function.client.WebClient;

public class ApiKeyAuthManager implements AuthenticationManager {
    private String TEST_KEY;
    private WebClient query;

    public ApiKeyAuthManager() {

    }

    public ApiKeyAuthManager(String TEST_KEY, WebClient query) {
        this.TEST_KEY = TEST_KEY;
        this.query = query;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        /* JWT 토큰 검증 */
        /* 1. 서명 검증 */
        /* 2. 권한 확인 */
        if (!TEST_KEY.equals(principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}

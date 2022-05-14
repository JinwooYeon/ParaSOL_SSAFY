package com.parasol.main_authentication.security.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    // Todo : 클라이언트 아이디로 바꾸기
    @Value("${authentication.baas.header}")
    private String API_KEY_AUTH_HEADER_NAME;

    public ApiKeyAuthFilter(ApiKeyAuthManager manager) {
        this.setAuthenticationManager(manager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(API_KEY_AUTH_HEADER_NAME);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}

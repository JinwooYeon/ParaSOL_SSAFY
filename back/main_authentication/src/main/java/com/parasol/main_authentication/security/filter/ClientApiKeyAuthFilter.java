package com.parasol.main_authentication.security.filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("clientApiKeyAuthFilter")
public class ClientApiKeyAuthFilter extends ApiKeyAuthFilter {

    public ClientApiKeyAuthFilter(@Qualifier("clientApiKeyAuthManager") ApiKeyAuthManager manager) {
        super(manager);
    }
}

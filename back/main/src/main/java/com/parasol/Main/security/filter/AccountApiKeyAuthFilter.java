package com.parasol.Main.security.filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("accountApiKeyAuthFilter")
public class AccountApiKeyAuthFilter extends ApiKeyAuthFilter {

    public AccountApiKeyAuthFilter(@Qualifier("accountApiKeyAuthManager") ApiKeyAuthManager manager) {
        super(manager);
    }
}

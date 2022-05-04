package com.parasol.Main.security.filter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("accountApiKeyAuthManager")
public class AccountApiKeyAuthManager extends ApiKeyAuthManager {

    public AccountApiKeyAuthManager() {
    }
}

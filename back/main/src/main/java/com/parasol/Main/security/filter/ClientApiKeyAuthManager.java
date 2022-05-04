package com.parasol.Main.security.filter;

import org.springframework.stereotype.Component;

@Component("clientApiKeyAuthManager")
public class ClientApiKeyAuthManager extends ApiKeyAuthManager {

    public ClientApiKeyAuthManager() {

    }
}

package com.parasol.main_authentication.service;

import com.parasol.main_authentication.entity.ApiKey;
import com.parasol.main_authentication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthenticateService extends AuthenticateService{
    private static final String pathName = "/account";

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean isValid(String ipAddr, String method, String endpoint, String clientId){
        if(!super.isValid(ipAddr, method, endpoint, clientId))
            return false;

        ApiKey apiKey = new ApiKey();
        apiKey.setClientId(clientId);

        return accountRepository.findByMethodAndClientIdAndPermitEndpoint(
                getMethodType(method),
                apiKey,
                getSubpath(pathName, endpoint)
        ) != null;
    }
}

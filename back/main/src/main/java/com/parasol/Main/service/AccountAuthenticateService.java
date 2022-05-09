package com.parasol.Main.service;

import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.repository.AccountRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthenticateService extends AuthenticateService{
    private static final String pathName = "/account";
    @Autowired
    private AccountRepositorySupport accountRepositorySupport;

    @Override
    public boolean isValid(String ipAddr, String method, String endpoint, String clientId){
        if(!super.isValid(ipAddr, method, endpoint, clientId))
            return false;

        ApiKey apiKey = new ApiKey();
        apiKey.setClientId(clientId);

        if(accountRepositorySupport.getAccountCnt(getMethodType(method), getSubpath(pathName, endpoint), apiKey) == null)
            return false;

        return true;
    }
}

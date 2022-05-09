package com.parasol.Main.service;

import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.repository.ClientRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientAuthenticateService extends AuthenticateService {
    private static final String pathName = "/client";
    @Autowired
    private ClientRepositorySupport clientRepositorySupport;

    @Override
    public boolean isValid(String ipAddr, String method, String endpoint, String clientId) {
        if(!super.isValid(ipAddr, method, endpoint, clientId))
            return false;

        ApiKey apiKey = new ApiKey();
        apiKey.setClientId(clientId);

        if(clientRepositorySupport.getClientCnt(getMethodType(method), getSubpath(pathName, endpoint), apiKey) == null)
            return false;

        return true;
    }
}

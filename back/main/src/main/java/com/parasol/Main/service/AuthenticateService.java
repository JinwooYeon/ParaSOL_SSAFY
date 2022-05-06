package com.parasol.Main.service;

import com.parasol.Main.eenum.MethodType;
import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticateService {
    @Autowired
    private ApiKeyRepository apiKeyRepository;
    private HashMap<String, MethodType> methodHash;

    public AuthenticateService() {
        this.methodHash = new HashMap<>();
        MethodType[] values = MethodType.values();

        for(int i = 0;i < MethodType.values().length;i++)
            methodHash.put(values[i].toString(), values[i]);
    }

    public boolean isValid(String ipAddr, String method, String endpoint, String clientId){

//        System.out.println(ipAddr);
//        System.out.println(methodHash.get(method));
//        System.out.println(endpoint);
//        System.out.println(clientId);

        Optional<ApiKey> apiKey = apiKeyRepository.findById(clientId);

        if(apiKey.isEmpty())
            return false;
        if(!apiKey.get().getIpAddr().equals(ipAddr))
            return false;

        return true;
    }
}

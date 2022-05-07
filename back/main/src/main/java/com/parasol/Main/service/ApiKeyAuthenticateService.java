package com.parasol.Main.service;

import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class ApiKeyAuthenticateService extends AuthenticateService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean register(String apiKey) {
        try {
            apiKeyRepository.save(new ApiKey(apiKey, request.getRemoteAddr()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean revoke(String apiKey) {
        // Todo : 아직 구현 안함
        return true;
    }
}

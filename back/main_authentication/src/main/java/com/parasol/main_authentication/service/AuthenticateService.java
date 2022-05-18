package com.parasol.main_authentication.service;

import com.parasol.main_authentication.enums.MethodType;
import com.parasol.main_authentication.entity.ApiKey;
import com.parasol.main_authentication.repository.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.net.InetSocketAddress;
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

        for (int i = 0; i < MethodType.values().length; i++)
            methodHash.put(values[i].toString(), values[i]);
    }

    public boolean isValid(String ipAddr, String method, String endpoint, String clientId) {
        ApiKey apiKey = apiKeyRepository.findById(clientId)
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Authorization: " + clientId + ", IP: " + ipAddr); });

        String registeredIpAddr = apiKey.getIpAddr();

        if (!StringUtils.hasText(ipAddr))
            return false;

        return registeredIpAddr.equals(ipAddr);
    }

    public MethodType getMethodType(String method) {
        return methodHash.get(method);
    }

    public String getSubpath(String prefix, String path) {
        String subpath = path.substring(prefix.length());

        return subpath.length() == 0?"/":subpath;
    }
}

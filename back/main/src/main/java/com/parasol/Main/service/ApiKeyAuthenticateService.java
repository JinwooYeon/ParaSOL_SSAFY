package com.parasol.Main.service;

import com.parasol.Main.api_request.RegisterRequest;
import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.repository.ApiKeyRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class ApiKeyAuthenticateService extends AuthenticateService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public String register(RegisterRequest registerRequest) {
        try {
            return apiKeyRepository.save(new ApiKey(this.createRandom(), request.getRemoteAddr(), registerRequest.getCompanyName())).getClientId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean revoke(String apiKey) {
        // Todo : 아직 구현 안함
        return true;
    }

    private String createRandom() throws NoSuchAlgorithmException {
        return this.encrypt(Long.toString(apiKeyRepository.count()));
    }

    private String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}

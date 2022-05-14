package com.parasol.Main.service;

import com.parasol.Main.api_request.RegisterRequest;
import com.parasol.Main.api_response.RegisterResponse;
import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.repository.ApiKeyRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;


@Service
public class ApiKeyAuthenticateService extends AuthenticateService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public Mono<RegisterResponse> register(RegisterRequest registerRequest) {
        try {
            ApiKey apiKey = ApiKey.builder()
                    .clientId(this.createRandom())
                    .ipAddr(request.getRemoteAddr())
                    .companyName(registerRequest.getCompanyName())
                    .build();

            return Mono.just(
                    RegisterResponse.builder()
                            .clientId(apiKeyRepository.save(apiKey).getClientId())
                            .build()
            );
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
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

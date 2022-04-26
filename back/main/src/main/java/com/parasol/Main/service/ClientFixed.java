package com.parasol.Main.service;

import com.parasol.Main.api_model.ClientResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ClientFixed {
    public ClientResponse save(ClientResponse saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        // http://corebank.com/client   - POST
        WebClient webClient = WebClient.create("http://corebank.com");
        return null;
    }

    public List<ClientResponse> findById(String id) {
        // 코어 뱅킹 시스템 rest api 호출
        // http://corebank.com/client?id=아이디   - Get
        return null;
    }
};

package com.parasol.Main.service;

import com.parasol.Main.api_model.ClientResponse;
import com.parasol.Main.api_model.RegisterRequest;
import com.parasol.Main.api_request.AccountCloseRequest;
import com.parasol.Main.api_request.ClientRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClientFixed {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public ClientResponse createAddClientRequest(ClientRegisterRequest clientInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        // http://corebank.com/client   - POST

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/")
                .queryParam("name", "sun")
                .queryParam("residentNumber", "202204261234567")
                .build());

        // ClientResponse에 응답 데이터 로드
        // Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        return null;
    }

    public List<ClientResponse> createRemoveClientRequest(AccountCloseRequest clientInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        // http://corebank.com/client?id=아이디   - Get

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.DELETE);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/")
                .queryParam("name", "sun")
                .queryParam("residentNumber", "202204261234567")
                .build());

        // ClientResponse에 응답 데이터 로드
        // Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        return null;
    }
};

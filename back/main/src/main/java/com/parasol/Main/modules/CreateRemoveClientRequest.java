package com.parasol.Main.modules;

import com.parasol.Main.api_response.ClientResponse;
import com.parasol.Main.api_request.AccountCloseRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

//@Component
public class CreateRemoveClientRequest {
//    public List<ClientResponse> createRemoveClientRequest(AccountCloseRequest clientInfo) {
//        // 코어 뱅킹 시스템 rest api 호출
//        // http://corebank.com/client?id=아이디   - Get
//
//        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.DELETE);
//        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
//                .path("/account/")
//                .queryParam("name", "sun")
//                .queryParam("residentNumber", "202204261234567")
//                .build());
//
//        // ClientResponse에 응답 데이터 로드
//        // Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);
//
//        return null;
//    }
}

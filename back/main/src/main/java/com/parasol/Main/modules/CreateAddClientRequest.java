package com.parasol.Main.modules;

import com.parasol.Main.api_model.ClientResponse;
import com.parasol.Main.api_request.ClientRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreateAddClientRequest {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    void createAddClientRequest() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/client")
                .build()
        ).body(BodyInserters
                .fromFormData("name", "sun")
                .with("residentNumber", "202204261234567")
        );
        // ClientResponse에 응답 데이터 로드
        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        System.out.println(response.block());
    }

//    public ClientResponse createAddClientRequest(ClientRegisterRequest clientInfo) {
//        // 코어 뱅킹 시스템 rest api 호출
//        // http://corebank.com/client   - POST
//
//        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
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

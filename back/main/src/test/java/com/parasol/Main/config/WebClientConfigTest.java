package com.parasol.Main.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class WebClientConfigTest {
    @Autowired
    @Qualifier(value = "fixedText")
    WebClient webClient;

    @Test
    void fixedTextTransport() {
        /********** 클라이언트 영역 **********/
        // 헤더설정, Optional
        // ...

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/")
                .queryParam("name", "sun")
                .queryParam("residentNumber", "202204261234567")
                .build());
        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        System.out.println(response.block());
    }
}
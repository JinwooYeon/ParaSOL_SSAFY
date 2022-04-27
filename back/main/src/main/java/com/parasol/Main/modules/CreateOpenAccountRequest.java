package com.parasol.Main.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreateOpenAccountRequest {
    @Autowired
    @Qualifier(value = "fixedText")
    WebClient webClient;

    void createOpenAccountRequest() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri("/account")
                .body(BodyInserters
                        .fromFormData("name", "sun")
                        .with("residentNumber", "2022042-1234567")
                        .with("accountPassword", "1234")
                );
        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        System.out.println(response.block());
    }
}

package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountOpenRequest;
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

    public void createOpenAccountRequest(AccountOpenRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri("/account")
                .body(BodyInserters
                        .fromFormData("name", request.getName())
                        .with("residentNumber", request.getResidentNumber())
                        .with("accountPassword", String.valueOf(request.getAccountPassword()))
                );
        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        System.out.println(response.block());
    }
}

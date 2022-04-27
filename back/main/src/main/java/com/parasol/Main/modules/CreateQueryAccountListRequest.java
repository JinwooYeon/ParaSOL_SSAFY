package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountListQueryRequest;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreateQueryAccountListRequest {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public AccountListQueryResultResponse createQueryAccountListResponse(AccountListQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account")
                .queryParam("name", request.getName())
                .queryParam("residentNumber", request.getResidentNumber())
                .build());

        Mono<AccountListQueryResultResponse> response = bodySpec.retrieve().bodyToMono(AccountListQueryResultResponse.class);

        return response.block();
    }
}

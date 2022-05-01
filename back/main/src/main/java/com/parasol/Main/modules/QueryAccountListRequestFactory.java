package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountListQueryRequest;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class QueryAccountListRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public AccountListQueryResultResponse createQueryAccountListRequest(AccountListQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri("/account/list")
                .body(BodyInserters
                        .fromFormData("name", request.getName())
                        .with("residentNumber", request.getResidentNumber())
                );

        Mono<AccountListQueryResultResponse> response = bodySpec.retrieve().bodyToMono(AccountListQueryResultResponse.class);

        return response.block();
    }
}

package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.AccountListQueryRequest;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class QueryAccountListRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public Mono<AccountListQueryResultResponse> createQueryAccountListRequest(AccountListQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/list")
                .build());

        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(request));

//        Mono<AccountListQueryResultResponse> response = headersSpec.retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {});
        Mono<AccountListQueryResultResponse> response = headersSpec.retrieve().bodyToMono(AccountListQueryResultResponse.class);

        return response
                .filter(s -> true)
                .flatMap(s -> Mono.just(s));
    }
}

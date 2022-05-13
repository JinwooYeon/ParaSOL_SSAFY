package com.parasol.accountList.modules;

import com.parasol.accountList.api_request.AccountListQueryParam;
import com.parasol.accountList.api_response.AccountListQueryResult;
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

    public Mono<AccountListQueryResult> createQueryAccountListRequest(AccountListQueryParam request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> headersSpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/list")
                .build()
        )
                .body(BodyInserters.fromValue(request));
        return headersSpec.retrieve().bodyToMono(AccountListQueryResult.class);

    }
}

package com.parasol.pay.modules;

import com.parasol.pay.api_request.AccountBalanceQueryParam;
import com.parasol.pay.api_response.AccountBalanceQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class QueryAccountBalanceRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public Mono<AccountBalanceQueryResult> createQueryAccountBalanceRequest(AccountBalanceQueryParam request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> headersSpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/balance")
                .build()
        )
                .body(BodyInserters.fromValue(request));

        return headersSpec.retrieve().bodyToMono(AccountBalanceQueryResult.class);
    }
}

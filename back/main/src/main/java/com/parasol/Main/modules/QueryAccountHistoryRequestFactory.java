package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountHistoryQueryParam;
import com.parasol.Main.api_request.AccountHistoryQueryRequest;
import com.parasol.Main.api_response.AccountHistoryResult;
import com.parasol.Main.api_response.AccountHistoryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Mono;

@Component
public class QueryAccountHistoryRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public Mono<AccountHistoryResult> createQueryAccountHistoryRequest(AccountHistoryQueryParam request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> headersSpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/history")
                .build()
        )
                .body(BodyInserters.fromValue(request));


//        RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(request));
//        Mono<AccountHistoryResultResponse> response = headersSpec.retrieve().bodyToMono(AccountHistoryResultResponse.class);

        return headersSpec.retrieve().bodyToMono(AccountHistoryResult.class);
    }
}

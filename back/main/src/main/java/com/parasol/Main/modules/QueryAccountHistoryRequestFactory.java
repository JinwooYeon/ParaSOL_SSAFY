package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountHistoryQueryRequest;
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

    public Mono<AccountHistoryResultResponse> createQueryAccountHistoryRequest(AccountHistoryQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/history")
                .build());

        RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(request));

        // TODO: 로직 정비 필요 (당장 배포를 위해 임의로 수정)
        Mono<AccountHistoryResultResponse> response = headersSpec.retrieve().bodyToMono(AccountHistoryResultResponse.class);

        return response
                .filter(s -> true)
                .flatMap(s -> Mono.just(s));
    }
}

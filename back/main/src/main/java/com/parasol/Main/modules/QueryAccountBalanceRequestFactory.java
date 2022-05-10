package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountBalanceQueryRequest;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
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

    public Mono<AccountBalanceQueryResultResponse> createQueryAccountBalanceRequest(AccountBalanceQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/balance")
                .build());

        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(request));

        // TODO: 로직 정비 필요 (당장 배포를 위해 임의로 수정)
        Mono<String> response = headersSpec.retrieve().bodyToMono(String.class);

        return response
                .filter(s -> !s.isEmpty())
                .flatMap(s -> {
                    AccountBalanceQueryResultResponse accountBalanceQueryResultResponse = new AccountBalanceQueryResultResponse();
                    accountBalanceQueryResultResponse.setBalance(Long.parseLong(s));
                    return Mono.just(accountBalanceQueryResultResponse);
                });
    }
}

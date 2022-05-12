package com.parasol.Main.modules;

import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Mono;

@Component
public class DepositRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public Mono<AccountBalanceQueryResultResponse> run(DepositRequest saveInfo) {
        /* Http 통신 */
        UriSpec<RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/deposit")
                .build());
        RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(saveInfo));

        // TODO: 로직 정비 필요 (당장 배포를 위해 임의로 수정)
        Mono<AccountBalanceQueryResultResponse> response = headersSpec.retrieve().bodyToMono(AccountBalanceQueryResultResponse.class);

        return response
                .filter(s -> true)
                .flatMap(s -> Mono.just(s));
    }
}

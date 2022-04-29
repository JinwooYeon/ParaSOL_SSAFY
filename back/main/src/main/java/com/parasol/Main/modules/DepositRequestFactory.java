package com.parasol.Main.modules;

import com.parasol.Main.api_request.DepositRequest;
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

    public boolean run(DepositRequest saveInfo) {
        /* Http 통신 */
        UriSpec<RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/deposit")
                .build());
        RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(saveInfo));

        /* 응답 결과 반환 */
        Mono<Boolean> exchange = headersSpec.exchangeToMono(response -> {
            if (response.statusCode().is2xxSuccessful()) {
                return response.bodyToMono(Boolean.class);
            } else if (response.statusCode().is4xxClientError()) {
                return null;
            } else if (response.statusCode().is5xxServerError()) {
                return null;
            }
            return null;
        });

        if (exchange != null && exchange.block())
            return true;

        return false;
    }
}

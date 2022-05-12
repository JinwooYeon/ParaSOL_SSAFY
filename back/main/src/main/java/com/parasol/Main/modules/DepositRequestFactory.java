package com.parasol.Main.modules;

import com.parasol.Main.api_request.DepositParam;
import com.parasol.Main.api_response.DepositResult;
import com.parasol.Main.api_response.TransactionExecutionResultResponse;
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

    public Mono<DepositResult> run(DepositParam saveInfo) {
        /* Http 통신 */
        UriSpec<RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        RequestHeadersSpec headersSpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/deposit")
                .build()
        )
                .body(BodyInserters.fromValue(saveInfo));

        return headersSpec.retrieve().bodyToMono(DepositResult.class);
    }
}

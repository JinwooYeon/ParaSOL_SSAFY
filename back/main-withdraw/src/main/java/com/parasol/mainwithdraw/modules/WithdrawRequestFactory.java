package com.parasol.mainwithdraw.modules;

import com.parasol.mainwithdraw.api_request.WithdrawParam;
import com.parasol.mainwithdraw.api_response.WithdrawResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WithdrawRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public Mono<WithdrawResult> run(WithdrawParam saveInfo){
        /* Http 통신 */
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> headersSpec = uriSpec.uri(uriBuilder -> uriBuilder
                        .path("/account/withdraw")
                        .build()
                )
                .body(BodyInserters.fromValue(saveInfo));

        return headersSpec.retrieve().bodyToMono(WithdrawResult.class);
    }
}

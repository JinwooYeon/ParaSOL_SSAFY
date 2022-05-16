package com.parasol.BaaS.modules;

import com.parasol.BaaS.api_param.DepositParam;
import com.parasol.BaaS.api_result.DepositResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DepositRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Value("${sbj-api-server.deposit}")
    private String endPoint;

    @Value("${baas.auth.key}")
    private String baasAuthKey;


    public Mono<DepositResult> create(DepositParam request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .build()
        )
                .header("Authorization", "Bearer " + baasAuthKey)
                .body(BodyInserters.fromValue(request));

        return bodySpec.retrieve().bodyToMono(DepositResult.class);
    }
}

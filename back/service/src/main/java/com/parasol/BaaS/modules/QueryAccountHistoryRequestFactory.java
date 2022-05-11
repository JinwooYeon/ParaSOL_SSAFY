package com.parasol.BaaS.modules;

import com.parasol.BaaS.api_model.AccountHistory;
import com.parasol.BaaS.api_request.QueryAccountHistoryRequest;
import com.parasol.BaaS.api_response.AccountHistoryQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class QueryAccountHistoryRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Value("${sbj-api-server.balance}")
    private String endPoint;

    @Value("${baas.auth.key}")
    private String baasAuthKey;

    public Mono<List<AccountHistory>> create(QueryAccountHistoryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .build()
        )
                .header("ClientId", baasAuthKey)
                .body(BodyInserters.fromValue(request));

        return bodySpec.retrieve().bodyToMono(new ParameterizedTypeReference<List<AccountHistory>>() {});
    }
}

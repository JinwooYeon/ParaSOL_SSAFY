package com.parasol.BaaS.modules;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_request.QueryAccountListRequest;
import com.parasol.BaaS.api_response.AccountListQueryResultResponse;
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
public class QueryAccountListRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Value("${sbj-api-server.account-list}")
    private String endPoint;

    @Value("${baas.auth.key}")
    private String baasAuthKey;

    public Mono<List<AccountInfo>> create(QueryAccountListRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .build()
        )
                .header("ClientId", baasAuthKey)
                .body(BodyInserters.fromValue(request));

        return bodySpec.retrieve().bodyToMono(new ParameterizedTypeReference<List<AccountInfo>>() {});
    }
}

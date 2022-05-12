package com.parasol.BaaS.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parasol.BaaS.api_param.WithdrawParam;
import com.parasol.BaaS.api_request.WithdrawRequest;
import com.parasol.BaaS.api_response.TransactionExecuteResultResponse;
import com.parasol.BaaS.api_result.DepositResult;
import com.parasol.BaaS.api_result.WithdrawResult;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WithdrawRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Value("${sbj-api-server.withdraw}")
    private String endPoint;

    @Value("${baas.auth.key}")
    private String baasAuthKey;


    public Mono<WithdrawResult> create(WithdrawParam request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .build()
        )
                .header("ClientId", baasAuthKey)
                .body(BodyInserters.fromValue(request));

        return bodySpec.retrieve().bodyToMono(WithdrawResult.class);
    }
}

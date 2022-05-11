package com.parasol.BaaS.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parasol.BaaS.api_request.BankLoginRequest;
import com.parasol.BaaS.api_request.DepositRequest;
import com.parasol.BaaS.api_response.BankLoginResultResponse;
import com.parasol.BaaS.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
public class BankLoginRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Value("${sbj-api-server.login}")
    private String endPoint;

    public BankLoginResultResponse create(BankLoginRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                        .path(endPoint)
                        .build()
                ).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue(request)
                );

        Mono<BankLoginResultResponse> response = bodySpec.retrieve().bodyToMono(BankLoginResultResponse.class);

        return response.block();
    }
}

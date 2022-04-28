package com.parasol.BaaS.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parasol.BaaS.api_request.DepositRequest;
import com.parasol.BaaS.api_request.WithdrawRequest;
import com.parasol.BaaS.api_response.TransactionExecuteResultResponse;
import org.apache.tomcat.util.json.JSONParser;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sbj-api-server.deposit}")
    private String endPoint;

    public TransactionExecuteResultResponse create(DepositRequest request) throws JsonProcessingException {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .build()
        ).body(BodyInserters
                .fromFormData("method", request.getMethod().toString())
                .with("amount", request.getAmount().toString())
                .with("accountFrom", request.getAccountFrom().toString())
                .with("accountTo", request.getAccountTo().toString())
        );
        // ClientResponse에 응답 데이터 로드
        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        TransactionExecuteResultResponse formattedResponse = objectMapper.readValue(response.block(), TransactionExecuteResultResponse.class);

        return formattedResponse;
    }
}

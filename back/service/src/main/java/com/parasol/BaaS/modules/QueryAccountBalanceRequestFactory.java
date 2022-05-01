package com.parasol.BaaS.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parasol.BaaS.api_request.DepositRequest;
import com.parasol.BaaS.api_request.QueryAccountBalanceRequest;
import com.parasol.BaaS.api_response.AccountBalanceQueryResultResponse;
import com.parasol.BaaS.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class QueryAccountBalanceRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sbj-api-server.balance}")
    private String endPoint;

    public AccountBalanceQueryResultResponse create(QueryAccountBalanceRequest request) throws JsonProcessingException {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .queryParam("bankName", request.getBankName())
                .queryParam("bankAccountNumber", request.getBankAccountNumber())
                .build()
        );

        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        AccountBalanceQueryResultResponse formattedResponse = objectMapper.readValue(response.block(), AccountBalanceQueryResultResponse.class);
        formattedResponse.setBankName(request.getBankName());
        formattedResponse.setBankAccountName(request.getBankAccountNumber());

        return formattedResponse;
    }
}

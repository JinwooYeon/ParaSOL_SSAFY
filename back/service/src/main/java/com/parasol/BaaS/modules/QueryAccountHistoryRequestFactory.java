package com.parasol.BaaS.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parasol.BaaS.api_request.QueryAccountBalanceRequest;
import com.parasol.BaaS.api_request.QueryAccountHistoryRequest;
import com.parasol.BaaS.api_response.AccountBalanceQueryResultResponse;
import com.parasol.BaaS.api_response.AccountHistoryQueryResultResponse;
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
public class QueryAccountHistoryRequestFactory {

    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sbj-api-server.balance}")
    private String endPoint;

    public AccountHistoryQueryResultResponse create(QueryAccountHistoryRequest request) throws JsonProcessingException {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path(endPoint)
                .queryParam("bankName", request.getBankName())
                .queryParam("bankAccountNumber", request.getBankAccountNumber())
                .build()
        );
        // ClientResponse에 응답 데이터 로드
        Mono<String> response = bodySpec.retrieve().bodyToMono(String.class);

        AccountHistoryQueryResultResponse formattedResponse = objectMapper.readValue(response.block(), AccountHistoryQueryResultResponse.class);

        return formattedResponse;
    }
}

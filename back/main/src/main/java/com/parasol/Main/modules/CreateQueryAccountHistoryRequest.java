package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountHistoryQueryRequest;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreateQueryAccountHistoryRequest {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public AccountHistoriesQueryResultResponse createQueryAccountHistoryRequest(AccountHistoryQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/history")
                .queryParam("accountNo", request.getBankAccountNumber())
                .build());

        Mono<AccountHistoriesQueryResultResponse> response = bodySpec.retrieve().bodyToMono(AccountHistoriesQueryResultResponse.class);

        return response.block();
    }
}

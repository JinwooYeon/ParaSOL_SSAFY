package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountBalanceQueryRequest;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class QueryAccountBalanceRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public AccountBalanceQueryResultResponse createQueryAccountBalanceRequest(AccountBalanceQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/balance")
                .queryParam("accountNo", request.getBankAccountNumber())
                .build());

        Mono<AccountBalanceQueryResultResponse> response = bodySpec.retrieve().bodyToMono(AccountBalanceQueryResultResponse.class);

        return response.block();
    }
}

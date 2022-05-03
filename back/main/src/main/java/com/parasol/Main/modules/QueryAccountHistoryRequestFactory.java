package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_request.AccountHistoryQueryRequest;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryAccountHistoryRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient webClient;

    public Mono<List<AccountHistory>> createQueryAccountHistoryRequest(AccountHistoryQueryRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/history")
                .queryParam("accountNo", request.getBankAccountNumber())
                .build());

        // TODO: 로직 정비 필요 (당장 배포를 위해 임의로 수정)
        Mono<List<AccountHistory>> response = bodySpec.retrieve().bodyToMono(new ParameterizedTypeReference<List<AccountHistory>>() {});

        return response
                .filter(s -> true)
                .flatMap(s -> Mono.just(s));
    }
}

package com.parasol.mainwithdraw.modules;

import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.TransactionExecutionResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WithdrawRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public Mono<TransactionExecutionResultResponse> run(WithdrawRequest saveInfo){
        /* Http 통신 */
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/withdraw")
                .build());
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(saveInfo));

        // TODO: 로직 정비 필요 (당장 배포를 위해 임의로 수정)
        Mono<TransactionExecutionResultResponse> response = headersSpec.retrieve().bodyToMono(TransactionExecutionResultResponse.class);

        return response
                .filter(s -> true)
                .flatMap(s -> Mono.just(s));
    }
}

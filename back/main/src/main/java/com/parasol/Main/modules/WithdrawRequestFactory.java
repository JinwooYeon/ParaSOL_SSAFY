package com.parasol.Main.modules;

import com.parasol.Main.api_request.WithdrawRequest;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
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

    public TransactionExecuteResultResponse run(WithdrawRequest saveInfo){
        /* Http 통신 */
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/withdraw")
                .build());
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue(saveInfo));
        Mono<Boolean> response = headersSpec.retrieve().bodyToMono(Boolean.class);

        /* TransactionExecuteResultResponse 에 응답 결과 담기 */
        // Todo: ...

        System.out.println(response.block());

        return null;
    }
}

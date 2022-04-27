package com.parasol.Main.modules;

import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import reactor.core.publisher.Mono;

@Component
public class DepositRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public TransactionExecuteResultResponse run(DepositRequest saveInfo){
        /* 파라메터 */
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("amount", 4500000000L);
        map.add("account_from", saveInfo.getAccountFrom());
        map.add("account_to", saveInfo.getAccountTo());

        /* Http 통신 */
        UriSpec<RequestBodySpec> uriSpec = fixedText.method(HttpMethod.POST);
        RequestBodySpec bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                .path("/account/deposit/")
                .build());
        RequestHeadersSpec<?> headersSpec = bodySpec.body(
                BodyInserters.fromValue(map));

        Mono<Boolean> response = headersSpec.retrieve().bodyToMono(Boolean.class);

        /* TransactionExecuteResultResponse 에 응답 결과 담기 */
        // Todo: ...

        return null;
    }
}

package com.parasol.BaaS.modules;

import com.parasol.BaaS.api_param.BankLoginParam;
import com.parasol.BaaS.api_param.OAuthLoginParam;
import com.parasol.BaaS.api_result.BankLoginResult;
import com.parasol.BaaS.api_result.OAuthLoginResult;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;


@Component
public class OAuthRequestFactory {
    @Value("${baas.auth.key}")
    private String baasAuthKey;

    @Value("${sbj-api-server.base-url}")
    private String serverAddress;
    @Value("${webclient.timeout.connect}")
    private int connTimeout;
    @Value("${webclient.timeout.response}")
    private int resTimeout;
    @Value("${webclient.timeout.read}")
    private int readTimeout;
    @Value("${webclient.timeout.write}")
    private int writeTimeout;

    public Mono<OAuthLoginResult> create(String endPoint, OAuthLoginParam request) {
        HttpClient httpClient = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connTimeout)
                .responseTimeout(Duration.ofMillis(resTimeout))
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

        WebClient webClient = WebClient
                .builder()
                .baseUrl(endPoint)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", endPoint))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        WebClient.RequestHeadersSpec<?> bodySpec = uriSpec.uri(uriBuilder -> uriBuilder
                        .path("")
                        .build()
                )
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        //.fromValue(request)
                        .fromFormData("code", request.getCode())
                        .with("client_id", request.getClientId())
                        .with("client_secret", request.getClientSecret())
                        .with("grant_type", request.getGrantType())
                        .with("redirect_uri", request.getRedirectUri())
                );

        return bodySpec.retrieve().bodyToMono(OAuthLoginResult.class);
    }
}

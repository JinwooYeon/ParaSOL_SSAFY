package com.parasol.Main.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class WebClientConfig {
    // application.properties에 있는 값 읽어오기
    @Value("${external-server.core-banking}")
    private String serverAddress;
    @Value("${webclient.timeout.connect}")
    private int connTimeout = 5000;
    @Value("${webclient.timeout.response}")
    private int resTimeout = 5000;
    @Value("${webclient.timeout.read}")
    private int readTimeout = 5000;
    @Value("${webclient.timeout.write}")
    private int writeTimeout = 5000;

    @Bean("fixedText")
    public WebClient fixedTextTransport() {
        /* 요청 후 timeout 밀리초 이내로 응답 없으면 Fail */
        HttpClient httpClient = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connTimeout)
                .responseTimeout(Duration.ofMillis(resTimeout))
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")  // Todo: 계정계 IP 주소 할당
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "https://jsonplaceholder.typicode.com"))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        /********** 클라이언트 영역 **********/
        // 헤더설정, Optional
        // ...

        UriSpec<RequestBodySpec> uriSpec = webClient.method(HttpMethod.GET);
        RequestBodySpec bodySpec = uriSpec.uri("/todos");

        WebClient.ResponseSpec responseSpec = bodySpec.retrieve();

        // System.out.println(responseSpec.bodyToMono(String.class).flux().blockFirst());

        return null;
    }
}
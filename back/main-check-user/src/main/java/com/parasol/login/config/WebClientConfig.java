package com.parasol.login.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
    // application.properties에 있는 값 읽어오기
    @Value("${external-server.core-banking}")
    private String serverAddress;
    @Value("${webclient.timeout.connect}")
    private int connTimeout;
    @Value("${webclient.timeout.response}")
    private int resTimeout;
    @Value("${webclient.timeout.read}")
    private int readTimeout;
    @Value("${webclient.timeout.write}")
    private int writeTimeout;

    @Bean(name = "fixedText")
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
                .baseUrl(serverAddress)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", serverAddress))
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        return webClient;
    }
}
package com.parasol.Main.modules;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CreateCloseAccountRequest {
    @Autowired
    @Qualifier(value = "fixedText")
    WebClient webClient;

    void createCloseAccountRequest() throws NotImplementedException {
        // TODO: 코어 미구현으로 패스
        throw new NotImplementedException("코어 미구현");
    }
}

package com.parasol.Main.modules;

import com.parasol.Main.api_request.ClientRegisterRequest;
import com.parasol.Main.api_response.ClientCreateResponse;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateAddClientRequestTest {
    @Autowired
    private CreateAddClientRequest addClientRequest;

    @Test
    void createAddClientRequest() throws InterruptedException {
        int cnt = 1000000;
        
        for(int i = 0;i < 100;i++) {
            ClientRegisterRequest request = new ClientRegisterRequest();

            request.setName("선민기");
            request.setResidentNumber("950809-" + Integer.toString(cnt++));

            Mono<ClientCreateResponse> addClientRequest = this.addClientRequest.createAddClientRequest(request);
            addClientRequest.subscribe(s->{
                System.out.println(s);
            });
        }

        Thread.sleep(10000L);
    }
}
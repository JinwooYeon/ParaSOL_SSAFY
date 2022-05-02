package com.parasol.Main.modules;

import com.parasol.Main.api_request.ClientRegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateAddClientRequestTest {

    @Autowired
    private CreateAddClientRequest createAddClientRequest;

    @Test
    void createAddClientRequest() throws InterruptedException {
        ClientRegisterRequest saveInfo = new ClientRegisterRequest();

        saveInfo.setResidentNumber("220502-2000000");
        saveInfo.setName("김싸피");

        List<Mono<String>> result = new ArrayList<>();

        for(int i = 0;i < 1; i++){
            result.add(createAddClientRequest.createAddClientRequest(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중
    }
}
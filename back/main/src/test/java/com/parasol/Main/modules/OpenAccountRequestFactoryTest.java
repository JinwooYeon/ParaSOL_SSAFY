package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountOpenRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAccountRequestFactoryTest {
    @Autowired
    private OpenAccountRequestFactory openAccountRequestFactory;

    @Test
    void createOpenAccountRequest() throws InterruptedException {
        AccountOpenRequest saveInfo = new AccountOpenRequest();

        saveInfo.setName("ssafy");
        saveInfo.setAccountPassword(1234);
        saveInfo.setResidentNumber("220428-2000001");

        List<Mono<String>> result = new ArrayList<>();

        for (int i = 0;i < 1;i++) {
            result.add(openAccountRequestFactory.createOpenAccountRequest(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중
    }
}
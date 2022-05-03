package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountListQueryRequest;
import com.parasol.Main.api_response.AccountListQueryResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryAccountListRequestFactoryTest {

    @Autowired
    private QueryAccountListRequestFactory queryAccountListRequestFactory;

    @Test
    void createQueryAccountListRequest() throws InterruptedException {
        AccountListQueryRequest saveInfo = new AccountListQueryRequest();

        saveInfo.setName("ssafy");
        saveInfo.setResidentNumber("220428-2000000");

        List<Mono<AccountListQueryResultResponse>> result = new ArrayList<>();

        for(int i = 0;i < 1; i++){
            result.add(queryAccountListRequestFactory.createQueryAccountListRequest(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중

    }
}
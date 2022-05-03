package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountHistoryQueryRequest;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class QueryAccountHistoryRequestFactoryTest {

    @Autowired
    private QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;

    @Test
    void createQueryAccountHistoryRequest() throws InterruptedException {
        AccountHistoryQueryRequest saveInfo = new AccountHistoryQueryRequest();

        saveInfo.setBankName("SBJ");
        saveInfo.setBankAccountNumber("188-158-441077");

        List<Mono<AccountHistoriesQueryResultResponse>> result = new ArrayList<>();

        for(int i = 0;i < 1; i++){
            result.add(queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중

    }

}
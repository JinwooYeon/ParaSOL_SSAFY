package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountBalanceQueryRequest;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class QueryAccountBalanceRequestFactoryTest {

    @Autowired
    private QueryAccountBalanceRequestFactory queryAccountBalanceRequestFactory;

    @Test
    void createQueryAccountBalanceRequest() throws InterruptedException {
        AccountBalanceQueryRequest saveInfo = new AccountBalanceQueryRequest();

        saveInfo.setBankName("SBJ");
        saveInfo.setBankAccountNumber("188-158-441077");

        List<Mono<AccountBalanceQueryResultResponse>> result = new ArrayList<>();

        for(int i = 0;i < 1; i++){
            result.add(queryAccountBalanceRequestFactory.createQueryAccountBalanceRequest(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중

    }

}
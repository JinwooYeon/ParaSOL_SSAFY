package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountHistory;
import com.parasol.Main.api_request.AccountHistoryQueryRequest;
import com.parasol.Main.api_response.AccountHistoriesQueryResultResponse;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootTest
class QueryAccountHistoryRequestFactoryTest {
    @Autowired
    private QueryAccountHistoryRequestFactory queryAccountHistoryRequestFactory;

    @Test
    void createQueryAccountHistoryRequest() throws InterruptedException {
        AccountHistoryQueryRequest saveInfo = new AccountHistoryQueryRequest();
        saveInfo.setBankAccountNumber("935-837-145298");
        saveInfo.setBankName("pipi");

        Mono<List<AccountHistory>> result = queryAccountHistoryRequestFactory.createQueryAccountHistoryRequest(saveInfo);

        result.subscribe(res ->{
            for(AccountHistory tmp : res) {
                System.out.println(tmp);
            }
        });

        Thread.sleep(5000);
    }
}
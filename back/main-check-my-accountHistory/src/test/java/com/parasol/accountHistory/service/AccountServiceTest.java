package com.parasol.accountHistory.service;

import com.parasol.accountHistory.api_request.AccountHistoryQueryRequest;
import com.parasol.accountHistory.api_response.AccountHistoryResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void getHistory() throws InterruptedException {
        AccountHistoryQueryRequest request = new AccountHistoryQueryRequest();

        request.setAccountNumber("123-123-123456");
        request.setPassword("khj12345");
        request.setId("khj123");

//        for(int i = 0;i < 100;i++) {
            Mono<AccountHistoryResultResponse> result = accountService.getHistory(request);

            result.subscribe(s->{
//                System.out.println(s.getHistory());
            });
//        }

        Thread.sleep(3000L);
    }
}
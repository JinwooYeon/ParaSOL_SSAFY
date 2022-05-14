package com.parasol.pay.service;

import com.parasol.pay.api_request.AccountBalanceQueryRequest;
import com.parasol.pay.api_response.AccountBalanceQueryResultResponse;
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
    void getBalance() throws InterruptedException {
        AccountBalanceQueryRequest request = new AccountBalanceQueryRequest();

        request.setAccountNumber("123-123-123456");
        request.setPassword("khj12345");
        request.setId("khj123");

        for(int i = 0;i < 100;i++) {
            Mono<AccountBalanceQueryResultResponse> balance = accountService.getBalance(request);

            balance.subscribe(s->{
                System.out.println(s.getBalance());
            });
        }

        Thread.sleep(3000L);
    }
}
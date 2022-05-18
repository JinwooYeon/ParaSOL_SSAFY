package com.parasol.pay.service;

import com.parasol.pay.api_request.AccountBalanceQueryRequest;
import com.parasol.pay.api_response.AccountBalanceQueryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

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
            Mono<AccountBalanceQueryResponse> balance = accountService.getBalance(request);

            balance.subscribe(s->{
                System.out.println(s.getBalance());
            });
        }

        Thread.sleep(3000L);
    }
}
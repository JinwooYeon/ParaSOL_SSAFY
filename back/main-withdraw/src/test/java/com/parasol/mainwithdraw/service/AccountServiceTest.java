package com.parasol.mainwithdraw.service;

import com.parasol.mainwithdraw.api_model.AccountInfo;
import com.parasol.mainwithdraw.api_request.WithdrawRequest;
import com.parasol.mainwithdraw.api_response.WithdrawResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    void withdraw() throws InterruptedException {
        WithdrawRequest request = new WithdrawRequest();
        AccountInfo accountInfo = new AccountInfo();

        accountInfo.setAccountNumber("123-123-111111");

        request.setAccountFrom(accountInfo);
        request.setAccountPassword("1234");
        request.setAmount(1L);
        // request.setNameOpponent();

        for (int i = 0;i < 10;i++) {
            Mono<WithdrawResponse> withdraw = accountService.withdraw(request);

            withdraw.subscribe(s->{
                System.out.println(s);
            });
        }

        Thread.sleep(1000L);
    }
}
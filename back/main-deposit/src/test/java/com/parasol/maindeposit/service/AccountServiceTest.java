package com.parasol.maindeposit.service;

import com.parasol.maindeposit.api_model.AccountInfo;
import com.parasol.maindeposit.api_request.DepositRequest;
import com.parasol.maindeposit.api_response.DepositResponse;
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
    void deposit() throws InterruptedException {
        DepositRequest request = new DepositRequest();
        AccountInfo accounto = new AccountInfo();

        accounto.setAccountNumber("123-123-111111");

        request.setNameFrom("noname");
        request.setAccountTo(accounto);
        request.setAmount(10L);

        for(int i = 0;i < 100;i++) {
            Mono<DepositResponse> deposit = accountService.deposit(request);

            deposit.subscribe(s -> {
                System.out.println(s);
            });
        }

        Thread.sleep(3000L);
    }
}
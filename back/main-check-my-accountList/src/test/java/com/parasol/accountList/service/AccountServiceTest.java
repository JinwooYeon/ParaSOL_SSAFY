package com.parasol.accountList.service;

import com.parasol.accountList.api_request.AccountListQueryRequest;
import com.parasol.accountList.api_response.AccountListQueryResultResponse;
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
    void getAllAccount() throws InterruptedException {
        AccountListQueryRequest request = new AccountListQueryRequest();

        request.setPassword("khj12345");
        request.setId("khj123");

//        for(int i = 0;i < 100;i++) {
            Mono<AccountListQueryResultResponse> result = accountService.getAllAccount(request);

            result.subscribe(s->{
                System.out.println(s.getAccounts());
            });
//        }



        Thread.sleep(3000L);
    }


}
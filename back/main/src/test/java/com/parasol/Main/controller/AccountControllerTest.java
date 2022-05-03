package com.parasol.Main.controller;

import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Test
    void createAccount() {
    }

    @Test
    void getList() {
    }

    @Test
    void getBalance() {
        long answer = accountController.getBalance("papi", "188-158-441077").block().getBalance();
        AtomicInteger end = new AtomicInteger(5);

        System.out.println("잔액조회 테스트 시작");

        Mono<AccountBalanceQueryResultResponse> papi1 = accountController.getBalance("papi", "188-158-441077");
        Mono<AccountBalanceQueryResultResponse> papi2 = accountController.getBalance("papi", "188-158-441077");
        Mono<AccountBalanceQueryResultResponse> papi3 = accountController.getBalance("papi", "188-158-441077");
        Mono<AccountBalanceQueryResultResponse> papi4 = accountController.getBalance("papi", "188-158-441077");
        Mono<AccountBalanceQueryResultResponse> papi5 = accountController.getBalance("papi", "188-158-441077");

        papi1.subscribe(e->{
            assertEquals(answer, e.getBalance());
            end.getAndDecrement();
        });

        papi2.subscribe(e->{
            assertEquals(answer, e.getBalance());
            end.getAndDecrement();
        });

        papi3.subscribe(e->{
            assertEquals(answer, e.getBalance());
            end.getAndDecrement();
        });

        papi4.subscribe(e->{
            assertEquals(answer, e.getBalance());
            end.getAndDecrement();
        });

        papi5.subscribe(e->{
            assertEquals(answer, e.getBalance());
            end.getAndDecrement();
        });

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(0, end.get());
    }

    @Test
    void getHistory() {
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }
}
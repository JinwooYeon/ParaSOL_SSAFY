package com.parasol.core.service;

import com.parasol.core.api_model.BankUserCreateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankUserServiceTest {

    @Autowired
    private BankUserService bankUserService;

    @Test
    void create() {
        BankUserCreateRequest request = BankUserCreateRequest.builder()
                .name("ssafy")
                .residentNumber("220428-2000002")
                .id("parasol")
                .password("parasol!@#")
                .build();

        String result = bankUserService.create(request);
        Assertions.assertThat(result).isNotEmpty();
    }
}
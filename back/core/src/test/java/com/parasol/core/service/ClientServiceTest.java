package com.parasol.core.service;

import com.parasol.core.entity.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void 서비스_고객_고객정보생성() {
        // Given
        final String name = "김형준";
        final String residentNumber = "961234-1234567";

        // When
        String creationResult = clientService.create(name, residentNumber);
        Client findResult = clientService.findById(creationResult);

        // Then
        Assertions.assertThat(findResult.getName()).isEqualTo(name);
        Assertions.assertThat(findResult.getResidentNumber()).isEqualTo(residentNumber);
    }

    @Test
    void 서비스_고객_고객정보찾기() {
        // Given
        final String name1 = "김형준1";
        final String name2 = "김형준2";
        final String name3 = "김형준3";

        final String residentNumber1 = "961234-1234567";
        final String residentNumber2 = "961234-1234568";
        final String residentNumber3 = "961234-1234569";

        // When
        String creationResult1 = clientService.create(name1, residentNumber1);
        String creationResult2 = clientService.create(name2, residentNumber2);
        String creationResult3 = clientService.create(name3, residentNumber3);
        Client findResult = clientService.findById(creationResult2);

        // Then
        Assertions.assertThat(findResult.getId()).isEqualTo(creationResult2);
        Assertions.assertThat(findResult.getName()).isEqualTo(name2);
        Assertions.assertThat(findResult.getResidentNumber()).isEqualTo(residentNumber2);
    }
}
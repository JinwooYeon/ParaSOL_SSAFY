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
        Client client = new Client();
        client.setName("김형준");
        client.setResidentNumber("961234-1234567");

        // When
        Long creationResult = clientService.create(client);
        Client findResult = clientService.findById(creationResult);

        // Then
        Assertions.assertThat(creationResult).isEqualTo(client.getId());
        Assertions.assertThat(findResult.getId()).isEqualTo(client.getId());
        Assertions.assertThat(findResult.getName()).isEqualTo(client.getName());
        Assertions.assertThat(findResult.getResidentNumber()).isEqualTo(client.getResidentNumber());
    }

    @Test
    void 서비스_고객_고객정보찾기() {
        // Given
        Client client1 = new Client();
        client1.setName("김형준1");
        client1.setResidentNumber("961234-1234567");

        Client client2 = new Client();
        client2.setName("김형준2");
        client2.setResidentNumber("961234-1234568");

        Client client3 = new Client();
        client3.setName("김형준3");
        client3.setResidentNumber("961234-1234569");

        // When
        Long creationResult1 = clientService.create(client1);
        Long creationResult2 = clientService.create(client2);
        Long creationResult3 = clientService.create(client3);
        Client findResult = clientService.findById(creationResult2);

        // Then
        Assertions.assertThat(creationResult1).isEqualTo(client1.getId());
        Assertions.assertThat(creationResult2).isEqualTo(client2.getId());
        Assertions.assertThat(creationResult3).isEqualTo(client3.getId());
        Assertions.assertThat(findResult.getId()).isEqualTo(client2.getId());
        Assertions.assertThat(findResult.getName()).isEqualTo(client2.getName());
        Assertions.assertThat(findResult.getResidentNumber()).isEqualTo(client2.getResidentNumber());
    }
}
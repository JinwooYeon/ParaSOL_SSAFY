package com.parasol.core.controller;

import com.parasol.core.entity.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClientControllerTest {

    @Autowired
    private ClientController clientController;

    @Test
    void 컨트롤러_고객_고객정보생성() {
        //given
        String name = "김형준";
        String residentNumber = "961234-1234567";

        //when
        String creationResult = clientController.CreateClient(name, residentNumber);
        Client findResult = clientController.GetClient(creationResult);

        //then
        Assertions.assertThat(name).isEqualTo(findResult.getName());
        Assertions.assertThat(residentNumber).isEqualTo(findResult.getResidentNumber());
    }

    @Test
    void 컨트롤러_고객_고객정보조회() {
        //given
        String name = "김형준";
        String residentNumber = "961234-1234567";

        //when
        String creationResult = clientController.CreateClient(name, residentNumber);
        Client findResult = clientController.GetClient(creationResult);

        //then
        Assertions.assertThat(name).isEqualTo(findResult.getName());
        Assertions.assertThat(residentNumber).isEqualTo(findResult.getResidentNumber());
    }
}
package com.parasol.Main.repository;

import com.parasol.Main.eenum.MethodType;
import com.parasol.Main.entity.ApiKey;
import com.parasol.Main.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientRepositorySupportTest {

    @Autowired
    private ClientRepositorySupport clientRepositorySupport;

    @Test
    void getClientCnt() throws InterruptedException {

        ApiKey apiKey = new ApiKey();
        apiKey.setClientId("1234");
        apiKey.setIpAddr("127.0.0.11");

        Client client = new Client();
        client.setMethod(MethodType.GET);
        client.setPermitEndpoint("/");
        client.setClientId(apiKey);

        clientRepositorySupport.getClientCnt(client.getMethod(), client.getPermitEndpoint(), client.getClientId());
        System.out.println(clientRepositorySupport.getClientCnt(client.getMethod(), client.getPermitEndpoint(), client.getClientId()));
    }
}
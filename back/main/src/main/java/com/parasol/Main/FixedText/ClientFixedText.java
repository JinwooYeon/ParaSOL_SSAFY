package com.parasol.Main.FixedText;

import com.parasol.Main.api_model.Client;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ClientFixedText {
    public Client save(Client saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        // http://corebank.com/client   - POST
        WebClient webClient = WebClient.create("http://corebank.com");
        return null;
    }

    public List<Client> findById(String id) {
        // 코어 뱅킹 시스템 rest api 호출
        // http://corebank.com/client?id=아이디   - Get
        return null;
    }
};

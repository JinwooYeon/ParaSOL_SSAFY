package com.parasol.Main.service;

import com.parasol.Main.api_model.Client;
import com.parasol.Main.api_model.ClientInfo;
import com.parasol.Main.api_request.ClientRegisterRequest;
import com.parasol.Main.api_response.ClientCreateResponse;
import com.parasol.Main.modules.CreateAddClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    @Autowired
    private CreateAddClientRequest addClientRequest;

    public Mono<ClientCreateResponse> create(ClientRegisterRequest request) {
        return addClientRequest.createAddClientRequest(request);
    }

}

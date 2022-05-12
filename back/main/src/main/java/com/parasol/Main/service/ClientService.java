package com.parasol.Main.service;

import com.parasol.Main.api_model.Client;
import com.parasol.Main.api_model.ClientInfo;
import com.parasol.Main.api_request.ClientRegisterRequest;
import com.parasol.Main.api_response.ClientCreateResponse;
import com.parasol.Main.modules.CreateAddClientRequest;
import com.parasol.Main.modules.FindClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

    @Autowired
    private CreateAddClientRequest addClientRequest;

    @Autowired
    private FindClientRequest findClientRequest;

    public Mono<ClientCreateResponse> create(ClientRegisterRequest request) {
        return addClientRequest.createAddClientRequest(request);
    }

    public Mono<Client> findById(String id){
        return findClientRequest.findClientRequest(id);
    }

    public ClientInfo findByResidentNumber(String residentNumber){
        return null;
    }
}

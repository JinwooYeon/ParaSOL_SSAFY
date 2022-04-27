package com.parasol.Main.service;

import com.parasol.Main.api_model.ClientInfo;
import com.parasol.Main.api_request.ClientRegisterRequest;
import com.parasol.Main.modules.CreateAddClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private CreateAddClientRequest addClientRequest;

    public void create(ClientRegisterRequest request) {
        String name = request.getName();
        String resident = request.getResidentNumber();

        addClientRequest.createAddClientRequest(request);
    }

    public ClientInfo findById(String id){
        return null;
    }

    public ClientInfo findByResidentNumber(String residentNumber){
        return null;
    }
}

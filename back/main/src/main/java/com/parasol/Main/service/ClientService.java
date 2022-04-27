package com.parasol.Main.service;

import com.parasol.Main.api_model.ClientInfo;
import com.parasol.Main.api_request.ClientRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientFixed clientFixed;

    public void create(ClientRegisterRequest request) {
        String name = request.getName();
        String resident = request.getResidentNumber();

        clientFixed.createAddClientRequest(request);
    }

    public String findById(String id){
        return null;
    }

    public String findByResidentNumber(String residentNumber){
        return null;
    }
}

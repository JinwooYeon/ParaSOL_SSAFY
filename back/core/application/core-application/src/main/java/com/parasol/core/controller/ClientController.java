package com.parasol.core.controller;

import com.parasol.core.api_model.ClientCreateRequest;
import com.parasol.core.api_model.ClientCreateResponse;
import com.parasol.core.entity.Client;
import com.parasol.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public ClientCreateResponse CreateClient(
            @RequestBody @Valid ClientCreateRequest client
    ) {
        return clientService.create(client.getName(), client.getResidentNumber());
    }

    @GetMapping("client")
    public Client GetClient(
            @RequestParam("id") Long cusNo
    ) {
        Client result = clientService.findById(cusNo);

        return result;
    }
}

package com.parasol.core.controller;

import com.parasol.core.api_model.ClientCreateRequest;
import com.parasol.core.api_model.ClientInfo;
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
    public Long CreateClient(
            @RequestBody @Valid ClientCreateRequest client
    ) {
        Long result = clientService.create(client.getName(), client.getResidentNumber());

        return result;
    }

    @GetMapping("client")
    public Client GetClient(
            @RequestParam("id") Long id
    ) {
        Client result = clientService.findById(id);

        return result;
    }
}

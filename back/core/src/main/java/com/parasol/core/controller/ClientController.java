package com.parasol.core.controller;

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
    public String CreateClient(
            @RequestBody @Valid ClientInfo client
    ) {
        return clientService.create(client.getName());
    }

    @GetMapping("client")
    public Client GetClient(
            @RequestParam("id") String id
    ) {
        return clientService.findById(id);
    }
}

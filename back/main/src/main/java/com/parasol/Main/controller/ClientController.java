package com.parasol.Main.controller;

import com.parasol.Main.api_model.Client;
import com.parasol.Main.api_model.ClientInfo;
import com.parasol.Main.api_request.ClientRegisterRequest;
import com.parasol.Main.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public Mono<String> createClient(
            @RequestBody @Valid ClientRegisterRequest request
            ) {
        return clientService.create(request);
    }

    @GetMapping("client")
    public Mono<Client> getClient(
            @RequestParam("id") String id
    ) {
       return clientService.findById(id);
    }

}

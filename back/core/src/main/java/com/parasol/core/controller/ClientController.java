package com.parasol.core.controller;

import com.parasol.core.entity.Client;
import com.parasol.core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public String CreateClient(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        String result = clientService.create(name, residentNumber);

        return result;
    }

    @GetMapping("client")
    public Client GetClient(
            @RequestParam("id") String id
    ) {
        Client result = clientService.findById(id);

        return result;
    }
}

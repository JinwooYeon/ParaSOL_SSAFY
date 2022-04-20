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
    public Long CreateClient(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        Client newClient = new Client();
        newClient.setName(name);
        newClient.setResidentNumber(residentNumber);

        Long result = clientService.create(newClient);

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

package com.parasol.Main.controller;

import com.parasol.Main.api_model.ClientInfo;
import com.parasol.Main.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public String createClient(
            @RequestBody ClientInfo clientInfo
    ) {
        return null;
    }

    @GetMapping("client")
    public ClientInfo getClient(
            @RequestParam("id") String id
    ) {
        ClientInfo result = clientService.findById(id);
        return result;
    }

}

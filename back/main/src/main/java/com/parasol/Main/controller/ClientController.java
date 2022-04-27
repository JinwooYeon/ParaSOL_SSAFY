package com.parasol.Main.controller;

import com.parasol.Main.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public String createClient(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        return null;
    }

    @GetMapping("client")
    public String getClient(
            @RequestParam("id") String id
    ) {
        return null;
    }

}

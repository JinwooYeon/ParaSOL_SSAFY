package com.parasol.BaaS.controller;

import com.parasol.BaaS.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private ClientService clientService;
}

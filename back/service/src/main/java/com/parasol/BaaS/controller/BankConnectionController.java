package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.service.BankConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankConnectionController {

    @Autowired
    BankConnectionService bankConnectionService;

    @PostMapping("/bank")
    @ResponseStatus
    public int connectBank(
            @RequestBody BankConnectionRequest request
    ) {
        try {
            bankConnectionService.addBankConnection(request);
            return 200;
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
            return 400;
        }
    }
}

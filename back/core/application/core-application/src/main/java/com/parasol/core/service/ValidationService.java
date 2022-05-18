package com.parasol.core.service;

import com.parasol.core.VO.Balance;
import com.parasol.core.entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Service
@Validated
public class ValidationService {
    public long calculateBalance(@Valid Balance balance){
        return balance.getBalance();
    }
    public Client generateClient(@Valid Client client){
        return client;
    }
    public boolean equalPassword(String recievedPassword, String storedPassword){
        if(!recievedPassword.equals(storedPassword))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        return true;
    }
}

package com.parasol.core.service;

import com.parasol.core.VO.Balance;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class ValidationService {
    public long calculateBalance(@Valid Balance balance){
        return balance.getBalance();
    }
}

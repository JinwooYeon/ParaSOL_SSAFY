package com.parasol.core.controller;

import com.parasol.core.api_model.BankUserCreateRequest;
import com.parasol.core.entity.BankUser;
import com.parasol.core.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class BankUserController {

    @Autowired
    private BankUserService bankUserService;

    @PostMapping("")
    public String create(@RequestBody @Valid BankUserCreateRequest request) {

        String result = bankUserService.create(request);
        return result;
    }
}

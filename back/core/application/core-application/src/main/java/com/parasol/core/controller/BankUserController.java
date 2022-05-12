package com.parasol.core.controller;

import com.parasol.core.api_model.BankUserCreateRequest;
import com.parasol.core.api_model.BankUserCreateResponse;
import com.parasol.core.api_model.BankUserLoginRequest;
import com.parasol.core.api_model.BankUserLoginResponse;
import com.parasol.core.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class BankUserController {

    @Autowired
    private BankUserService bankUserService;

    @PostMapping("register")
    public BankUserCreateResponse createBankUser(@RequestBody @Valid BankUserCreateRequest request) {

        return bankUserService.createBankUser(request);
    }

    @PostMapping("login")
    @ResponseBody
    public BankUserLoginResponse login(@RequestBody @Valid BankUserLoginRequest request) {

        BankUserLoginResponse result = bankUserService.login(request);
        return result;
    }
}

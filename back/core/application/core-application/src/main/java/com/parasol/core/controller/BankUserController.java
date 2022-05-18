package com.parasol.core.controller;

import com.parasol.core.api_model.BankUserCreateRequest;
import com.parasol.core.api_model.BankUserCreateResponse;
import com.parasol.core.api_model.BankUserLoginRequest;
import com.parasol.core.api_model.BankUserLoginResponse;
import com.parasol.core.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class BankUserController {

    @Autowired
    private BankUserService bankUserService;

    @PostMapping("register")
    public ResponseEntity<BankUserCreateResponse> createBankUser(@RequestBody @Valid BankUserCreateRequest request) {
        BankUserCreateResponse response = bankUserService.createBankUser(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity<BankUserLoginResponse> login(@RequestBody @Valid BankUserLoginRequest request) {
        BankUserLoginResponse response = bankUserService.login(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

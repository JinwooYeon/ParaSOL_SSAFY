package com.parasol.core.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirmbankingController {

    @PostMapping("firmbanking")
    public String CreateFirmbanking(){

//        TODO: 펌뱅킹 등록
        return "";
    }
}

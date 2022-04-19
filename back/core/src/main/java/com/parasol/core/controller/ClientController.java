package com.parasol.core.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @PostMapping("client")
    public String CreateUser(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        // TODO: 유저 생성

        return "";
    }

    @DeleteMapping("client")
    public String DeleteUser(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        // TODO: 유저 삭제

        return "";
    }
}

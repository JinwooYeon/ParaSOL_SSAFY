package com.parasol.core.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @PostMapping("user")
    public String CreateUser(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        // TODO: 유저 생성

        return "";
    }

    @DeleteMapping("user")
    public String DeleteUser(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
        // TODO: 유저 삭제

        return "";
    }
}

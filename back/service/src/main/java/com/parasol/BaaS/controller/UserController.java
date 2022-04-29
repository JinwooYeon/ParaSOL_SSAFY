package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_request.UserRegisterRequest;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public String login(
            @RequestBody LoginRequest request
    ) {
        String id = request.getId();
        String password = request.getPassword();

        UserInfo user = userService.getUserByUserId(id);
        if(user == null) return null;

        // 나중에 토큰 반환
        if(password.equals(user.getPassword())) return "성공";
        return "실패";
    }

    @PostMapping("/register")
    public UserInfo registerUser(
            @RequestBody UserRegisterRequest request
    ) {
        System.out.println("request.getName() = " + request.getName());
        User user = userService.createUser(request);

        if(user == null) return null;
        return UserInfo.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .build();
    }



}

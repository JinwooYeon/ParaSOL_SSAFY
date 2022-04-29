package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_request.UserRegisterRequest;
import com.parasol.BaaS.api_request.UserUpdateRequest;
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
        User user = userService.createUser(request);

        if(user == null) return null;
        return UserInfo.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .build();
    }

    @PatchMapping
    public UserInfo updateUser(
            @RequestBody UserUpdateRequest request
    ){
        User user = userService.updateUser(request);

        if(user == null) return null;
        return UserInfo.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .build();
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(
            @PathVariable String userId
    ){
        boolean result = userService.deleteUser(userId);
        if(result) return "탈퇴 성공";
        return "탈퇴 실패";
    }


}

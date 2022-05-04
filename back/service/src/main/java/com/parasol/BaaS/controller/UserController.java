package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.RefreshToken;
import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_request.UserRegisterRequest;
import com.parasol.BaaS.api_request.UserUpdateRequest;
import com.parasol.BaaS.api_response.AuthTokenResponse;
import com.parasol.BaaS.api_response.UserInfoQueryResultResponse;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public AuthTokenResponse login(
            @RequestBody LoginRequest request
    ) {

        AuthToken token = userService.login(request);

        if(token == null) {
            return null;
        }

        // TODO : 토큰 반환
        return AuthTokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @PostMapping("/token")
    private AuthTokenResponse issueNewToken (
            Authentication authentication,
            @RequestBody RefreshToken refreshToken
    ) {
        if(authentication == null) {
            return null;
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String userId = userDetail.getUsername();

        AuthToken token = userService.issueAuthToken(userId, refreshToken.getRefreshToken());

        if(token == null) {
            return null;
        }

        return AuthTokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @GetMapping("/{userId}")
    public UserInfoQueryResultResponse getUser(
            @PathVariable String userId
    ){
        // TODO : PathVariable -> 토큰에서 userId 받기
        User user = userService.getUserByUserId(userId);

        if(user == null) return null;
        return UserInfoQueryResultResponse.builder()
                .id(user.getUserId())
//                .password(user.getPassword())
                .name(user.getUserName())
                .build();
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
        // TODO : 토큰에서 userId 받기
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
        // TODO : PathVariable -> 토큰에서 userId 받기
        boolean result = userService.deleteUser(userId);
        if(result) return "탈퇴 성공";
        return "탈퇴 실패";
    }
}

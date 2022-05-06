package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.RefreshToken;
import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.IdCheckRequest;
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

        return AuthTokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @PostMapping("/idcheck")
    public boolean idCheck(
            @RequestBody IdCheckRequest request
    ){
        User user = userService.getUserByUserId(request.getId());
        if(user == null) {
            return true;
        }
        return false;
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

    @GetMapping
    public UserInfoQueryResultResponse getUser(
            Authentication authentication
    ){
        if(authentication == null) {
            return null;
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String userId = userDetail.getUsername();

        User user = userService.getUserByUserId(userId);

        if(user == null) {
            return null;
        }

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
            Authentication authentication,
            @RequestBody UserUpdateRequest request
    ){
        if(authentication == null) {
            return null;
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String userId = userDetail.getUsername();

        User user = userService.updateUser(userId, request);

        if(user == null) return null;
        return UserInfo.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .build();
    }

    @DeleteMapping
    public String deleteUser(
            Authentication authentication
    ){
        if(authentication == null) {
            return null;
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String userId = userDetail.getUsername();

        boolean result = userService.deleteUser(userId);
        if(result) return "탈퇴 성공";
        return "탈퇴 실패";
    }
}

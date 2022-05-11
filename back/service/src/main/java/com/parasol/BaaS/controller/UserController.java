package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.AuthTokenResponse;
import com.parasol.BaaS.api_response.UserInfoQueryResultResponse;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${sbj-api-server.base-url}") String baseUrl;

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
                .accessToken(token.getAccessToken().getAccessToken())
                .refreshToken(token.getRefreshToken().getRefreshToken())
                .build();
    }

    @GetMapping("/login/google")
    public void loginGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = baseUrl + "/oauth2/authorization/google";
        response.sendRedirect(redirectUri);
    }

    @GetMapping("/login/oauth")
    public AuthTokenResponse loginOauth(@RequestParam String id) {
        if(id == null) {
            return null;
        }
        AuthToken token = userService.loginOauth(id);

        return AuthTokenResponse.builder()
                .accessToken(token.getAccessToken().getAccessToken())
                .refreshToken(token.getRefreshToken().getRefreshToken())
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

    @GetMapping("/token")
    private AuthTokenResponse reissueToken (
            Authentication authentication
    ) {
        if(authentication == null) {
            return null;
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();
        String refreshToken = authentication.getPrincipal().toString();

        AuthToken token = userService.reissueAuthToken(id, refreshToken);

        if(token == null) {
            return null;
        }

        return AuthTokenResponse.builder()
                .accessToken(token.getAccessToken().getAccessToken())
                .refreshToken(token.getRefreshToken().getRefreshToken())
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

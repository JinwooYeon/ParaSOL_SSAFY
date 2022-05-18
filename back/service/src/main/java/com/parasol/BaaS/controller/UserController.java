package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponse>> login (
            @RequestBody LoginRequest request
    ) {
        return userService.login(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("/login/google")
    public void loginGoogle(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String baseUrl = ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        String redirectUri = baseUrl + "/oauth2/authorization/google";
        response.sendRedirect(redirectUri);
    }

    @GetMapping("/login/oauth")
    public Mono<ResponseEntity<LoginResponse>> loginOauth (
            @RequestParam String id
    ) {
        LoginRequest request = LoginRequest.builder()
                .id(id)
                .build();

        return userService.loginOauth(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("/idcheck")
    public Mono<ResponseEntity<IdCheckResponse>> idCheck (
            @RequestBody IdCheckRequest request
    ) {
        return userService.idCheck(request)
                .map(response -> new ResponseEntity<>(null, HttpStatus.OK));
    }

    @PostMapping("/password")
    public Mono<ResponseEntity<PasswordResetResponse>> resetPassword (
            @RequestBody PasswordResetRequest request
    ) {
        return userService.resetPassword(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("/token")
    private Mono<ResponseEntity<ReissueTokenResponse>> reissueToken (
            Authentication authentication
    ) {
        ReissueTokenRequest request = ReissueTokenRequest.builder()
                .authentication(authentication)
                .build();

        return userService.reissueAuthToken(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity<QueryUserInfoResponse>> getUserInfo (
            Authentication authentication
    ) {
        QueryUserInfoRequest request = QueryUserInfoRequest.builder()
                .authentication(authentication)
                .build();

        return userService.getUserInfo(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<RegisterResponse>> registerUser(
            @RequestBody RegisterRequest request
    ) {
        return userService.createUser(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.CREATED));
    }

    @PatchMapping
    public Mono<ResponseEntity<UpdateResponse>> updateUser(
            Authentication authentication,
            @RequestBody PasswordUpdateRequest request
    ) {
        request.setAuthentication(authentication);

        return userService.updateUser(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.NO_CONTENT));
    }

    @DeleteMapping
    public Mono<ResponseEntity<DeleteResponse>> deleteUser(
            Authentication authentication
    ) {
        DeleteRequest request = DeleteRequest.builder()
                .authentication(authentication)
                .build();

        return userService.deleteUser(request)
                .map(response -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }
}

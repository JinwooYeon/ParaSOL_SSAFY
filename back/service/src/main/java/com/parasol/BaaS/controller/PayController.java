package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    @GetMapping
    @ResponseBody
    public Mono<ResponseEntity<PayInfoResponse>> getPayInfo(
            Authentication authentication
    ) {
        PayInfoRequest request = PayInfoRequest.builder()
                .authentication(authentication)
                .build();

        return payService.getPayInfo(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("transaction")
    public Mono<ResponseEntity<PayTransactionResponse>> doPayTransact(
            Authentication authentication,
            @RequestBody PayTransactionRequest request
    ) {
        request.setAuthentication(authentication);

        return payService.doPayTransact(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("charge")
    public Mono<ResponseEntity<PayChargeResponse>> doPayCharge(
            Authentication authentication,
            @RequestBody PayChargeRequest request
    ) {
        request.setAuthentication(authentication);

        return payService.doPayCharge(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("withdraw")
    public Mono<ResponseEntity<PayWithdrawResponse>> doPayWithdraw(
            Authentication authentication,
            @RequestBody PayWithdrawRequest request
    ) {
        request.setAuthentication(authentication);

        return payService.doPayWithdraw(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("history")
    public Mono<ResponseEntity<PayHistoryResponse>> getPayHistory(
            Authentication authentication,
            @RequestParam String month
    ) {
        PayHistoryRequest request = PayHistoryRequest.builder()
                .authentication(authentication)
                .month(month)
                .build();

        return payService.getPayHistory(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @GetMapping("/auth")
    public Mono<ResponseEntity<PayQueryTwoFactorResponse>> queryTwoFactor(
            Authentication authentication
    ) {
        PayQueryTwoFactorRequest request = PayQueryTwoFactorRequest.builder()
                .authentication(authentication)
                .build();

        return payService.queryTwoFactor(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @PostMapping("/auth/bio")
    public Mono<ResponseEntity<PayRegisterBioResponse>> registerBio(
            Authentication authentication,
            @RequestBody PayRegisterBioRequest request
    ) {
        request.setAuthentication(authentication);

        return payService.registerBio(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }

    @DeleteMapping("/auth/bio")
    public Mono<ResponseEntity<PayDeleteBioResponse>> registerBio(
            Authentication authentication
    ) {
        PayDeleteBioRequest request = PayDeleteBioRequest.builder()
                .authentication(authentication)
                .build();

        return payService.deleteBio(request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}

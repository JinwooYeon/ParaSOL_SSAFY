package com.parasol.core.controller;

import com.parasol.core.api_model.FirmbankingRegisterRequest;
import com.parasol.core.entity.Firmbanking;
import com.parasol.core.service.FirmbankingService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firm")
public class FirmbankingController {
    @Autowired
    private FirmbankingService firmbankingService;

    @PostMapping("/")
    public ResponseEntity<Firmbanking> createFirmbanking(
            @RequestBody @ApiParam(value = "펌뱅킹 등록 정보", required = true) FirmbankingRegisterRequest registerRequest
    ) {
        return ResponseEntity.status(200).body(firmbankingService.createFirmbanking(registerRequest));
    }

    @PatchMapping("/")
    public ResponseEntity<Firmbanking> updateFirmbanking(
            @RequestBody @ApiParam(value = "펌뱅킹 수정 정보", required = true) FirmbankingRegisterRequest updateRequest
    ) {
        return ResponseEntity.status(200).body(firmbankingService.updateFirmbanking(updateRequest));
    }

    @DeleteMapping("/")
    public String deleteFirmbanking(
            @RequestBody @ApiParam(value = "펌뱅킹 삭제 정보", required = true) FirmbankingRegisterRequest deleteRequest
    ) {
        firmbankingService.deleteFirmbanking(deleteRequest);
        return "";
    }

    // 이름으로 검색만 지원
    @GetMapping("/")
    public ResponseEntity<Firmbanking> getFirmbanking(
            @RequestParam(name = "name", required = true) String name
    ) {
        return ResponseEntity.status(200).body(firmbankingService.getFirmbanking(name).get());
    }

}

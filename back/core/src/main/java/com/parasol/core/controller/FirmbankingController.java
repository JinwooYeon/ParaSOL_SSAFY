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

    // 이름으로 검색
    @GetMapping("/name/{name}")
    public ResponseEntity<Firmbanking> getFirmbankingByName(
            @PathVariable(name = "이름") @ApiParam(value = "이름", required = true) String name
    ) {
        return ResponseEntity.status(200).body(firmbankingService.getFirmbankingByName(name).get());
    }

    // 이름과 타입으로 검색
    @GetMapping("/name/{name}/{type}")
    public ResponseEntity<Firmbanking> getFirmbankingByNameAndType(
            @PathVariable(name = "이름") @ApiParam(value = "이름", required = true) String name,
            @PathVariable(name = "타입") @ApiParam(value = "타입", required = true) String type
    ) {
        return ResponseEntity.status(200).body(firmbankingService.getFirmbankingByNameAndType(name, type).get());
    }

    // 출금계좌번호로 검색
    @GetMapping("/name/{withdrawAccount}")
    public ResponseEntity<Firmbanking> getFirmbankingByWithdrawAccountNo(
            @PathVariable(name = "출금계좌") @ApiParam(value = "출금계좌", required = true) String withdrawAccount
    ) {
        return ResponseEntity.status(200).body(firmbankingService.getFirmbankingByWithdrawAccount(withdrawAccount).get());
    }

    // 입금계좌번호로 검색
    @GetMapping("/name/{depositAccount}")
    public ResponseEntity<Firmbanking> getFirmbankingByDepositAccountNo(
            @PathVariable(name = "입금계좌") @ApiParam(value = "입금계좌", required = true) String depositAccount
    ) {
        return ResponseEntity.status(200).body(firmbankingService.getFirmbankingByDeposiAccount(depositAccount).get());
    }
}

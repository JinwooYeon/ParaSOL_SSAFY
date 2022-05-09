package com.parasol.core.grpc.client;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class GrpcClientController {

    private final GrpcClientService grpcClientService;

    @GetMapping("balance")
    public String getBalance(
            @RequestParam("accountNumber") String accountNumber
    ) {
        try {
            return grpcClientService.getBalance(accountNumber);
        } catch (StatusRuntimeException e) {
            return e.getMessage();
        }

    }
}

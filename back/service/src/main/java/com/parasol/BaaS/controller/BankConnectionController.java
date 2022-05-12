package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.api_response.BankConnectionResponse;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.BankConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BankConnectionController {

    @Autowired
    BankConnectionService bankConnectionService;

    @PostMapping("/bank")
    public Mono<ResponseEntity<BankConnectionResponse>> connectBank(
            Authentication authentication,
            @RequestBody BankConnectionRequest request
    ){
        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        User user = userDetail.getUser();

        return bankConnectionService.addBankConnection(user, request)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK));
    }
}

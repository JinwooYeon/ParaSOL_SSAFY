package com.parasol.BaaS.controller;

import com.parasol.BaaS.api_request.BankConnectionRequest;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.BankConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankConnectionController {

    @Autowired
    BankConnectionService bankConnectionService;

    @PostMapping("/bank")
    @ResponseStatus
    public int connectBank(
            Authentication authentication,
            @RequestBody BankConnectionRequest request
    ){
        if (authentication == null) {
            return 403;
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        User user = userDetail.getUser();

        try {
            bankConnectionService.addBankConnection(user, request);
            return 200;
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
            return 400;
        } catch (NullPointerException e) {
            System.out.println(e.toString());
            return 404;
        }
    }
}

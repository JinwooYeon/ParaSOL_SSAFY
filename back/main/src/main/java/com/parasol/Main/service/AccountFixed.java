package com.parasol.Main.service;

import com.parasol.Main.api_model.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountFixed {
    public AccountResponse save(AccountResponse saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public List<AccountResponse> findById(String id) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public List<AccountResponse> findByIdClient(AccountResponse account) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }
}

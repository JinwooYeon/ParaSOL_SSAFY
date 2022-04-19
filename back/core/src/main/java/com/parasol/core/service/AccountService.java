package com.parasol.core.service;

import com.parasol.core.entity.Account;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.utils.AccountManager;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String Create(Account account) {
        account.setAccountNo(AccountManager.GenerateAccountNumber());
        accountRepository.save(account);
        return account.getAccountNo();
    }
}

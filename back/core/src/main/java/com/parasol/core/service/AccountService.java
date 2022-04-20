package com.parasol.core.service;

import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.entity.Account;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.utils.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public String Create(Account account) {
        account.setAccountNo(AccountManager.GenerateAccountNumber());
        accountRepository.save(account);
        return account.getAccountNo();
    }

    public List<Account> findByClient(String name, String residentNumber) {
        return accountRepository.findAll();
    }





    public boolean deposit(AccountRequest request) {
        Account accountFrom = accountRepository.findByAccountNo(request.getAccountFrom().getBankAccountNumber());

        // 일단 같은 은행 계좌라고 생각하고 할게욥
        Account accountTo = accountRepository.findByAccountNo(request.getAccountTo().getBankAccountNumber());

        Long fromBalance = accountFrom.getBalance() - request.getAmount();
        Long toBalance = accountTo.getBalance() + request.getAmount();
        // from 계좌에서 입금 금액만큼 빼기
        accountFrom.setBalance(fromBalance);
        // to 계좌에서 입금 금액만큼 추가
        accountTo.setBalance(toBalance);

        return true;
    }

    public boolean withdraw(AccountRequest request) {
        Account accountFrom = accountRepository.findByAccountNo(request.getAccountFrom().getBankAccountNumber());

        // 일단 같은 은행 계좌라고 생각하고 할게욥
        Account accountTo = accountRepository.findByAccountNo(request.getAccountTo().getBankAccountNumber());

        Long fromBalance = accountFrom.getBalance() - request.getAmount();
        Long toBalance = accountTo.getBalance() + request.getAmount();
        // from 계좌에서 입금 금액만큼 빼기
        accountFrom.setBalance(fromBalance);
        // to 계좌에서 입금 금액만큼 추가
        accountTo.setBalance(toBalance);

        return true;
    }
}

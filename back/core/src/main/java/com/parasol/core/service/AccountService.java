package com.parasol.core.service;

import com.parasol.core.VO.Balance;
import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.Client;
import com.parasol.core.repository.AccountRepository;
import com.parasol.core.utils.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ValidationService validationService;

    public String Create(@Valid Account account) {
        account.setId(AccountManager.GenerateAccountNumber());
        accountRepository.save(account);
        return account.getId();
    }

    public List<Account> getAllAccount(@Valid Client client) {
        List<Account> accounts = accountRepository.findByClient(client);
        return accounts;
    }

    public Long getBalance(String accountNo) {
        Optional<Account> account = accountRepository.findById(accountNo);
        return account.get().getBalance();
    }

    @Transactional
    public boolean deposit(@Valid AccountRequest request) {
        // to 계좌에 입금
        // 일단 같은 은행 계좌라고 생각하고 할게욥
        Optional<Account> accountTo = accountRepository.findById(request.getAccountFrom().getBankAccountNumber());

        Long toBalance = accountTo.get().getBalance() + request.getAmount();
        // to 계좌에서 입금 금액만큼 추가
        accountTo.get().setBalance(toBalance);

        return true;
    }

    public boolean withdraw(@Valid AccountRequest request) {
        // from 계좌에서 출금
        Optional<Account> accountFrom = accountRepository.findById(request.getAccountFrom().getBankAccountNumber());

        // Long fromBalance = accountFrom.get().getBalance() - request.getAmount();
        Long fromBalance = validationService.calculateBalance(new Balance(accountFrom.get().getBalance() - request.getAmount()));
        // from 계좌에서 입금 금액만큼 빼기
        accountFrom.get().setBalance(fromBalance);

        return true;
    }

    public boolean remit(@Valid AccountRequest request) {

        /*Optional<Account> accountTo = accountRepository.findById(request.getAccountTo().getBankAccountNumber());
        Optional<Account> accountFrom = accountRepository.findById(request.getAccountFrom().getBankAccountNumber());

        Long toBalance = validationService.calculateBalance(new Balance(accountTo.get().getBalance() + request.getAmount()));
        Long fromBalance = validationService.calculateBalance(new Balance(accountFrom.get().getBalance() - request.getAmount()));

        accountTo.get().setBalance(toBalance);
        accountFrom.get().setBalance(fromBalance);*/

        return true;
    }
}

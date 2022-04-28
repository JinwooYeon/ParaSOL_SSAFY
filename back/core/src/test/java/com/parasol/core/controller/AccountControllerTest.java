package com.parasol.core.controller;

import com.parasol.core.api_model.AccountInfo;
import com.parasol.core.api_model.AccountRequest;
import com.parasol.core.eenum.TransactionType;
import com.parasol.core.entity.Account;
import com.parasol.core.entity.TransactionHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @Test
    void createAccount() {
        String result = accountController.createAccount("ssafy", "220428-2000000", 11);
        System.out.println(result);
    }

    @Test
    void getAllAccount() {
        List<Account> result = accountController.getAllAccount("ssafy", "220428-2000000");

        for(Account tmp : result){
            System.out.println(tmp.getId() + " / " + tmp.getClient() + " / $" + tmp.getBalance());
        }
    }

    @Test
    void getBalance() {
        long result = accountController.getBalance("225-169-673608");
        System.out.println(result);
    }

    @Test
    void getAccountHistory() {
        List<TransactionHistory> result = accountController.getAccountHistory("225-169-673608");

        for(TransactionHistory tmp : result){
            System.out.println(tmp);
        }
    }

    @Test
    void deposit() {
        AccountRequest request = new AccountRequest();
        AccountInfo from = new AccountInfo();
        AccountInfo to = new AccountInfo();

        to.setBankName("no bank");
        to.setBankAccountNumber("225-169-673608");

        from.setBankName("no bank");
        from.setBankAccountNumber("jun1");

        request.setType(TransactionType.DEPOSIT);
        request.setAmount(3000L);
        request.setAccountFrom(from);
        request.setAccountTo(to);

        boolean deposit = accountController.deposit(request);

        System.out.println(deposit);
    }

    @Test
    void withdraw() {
        AccountRequest request = new AccountRequest();
        AccountInfo from = new AccountInfo();
        AccountInfo to = new AccountInfo();

        from.setBankName("no bank");
        from.setBankAccountNumber("225-169-673608");

        to.setBankName("no bank");
        to.setBankAccountNumber("225-169-673608");

        request.setType(TransactionType.WITHDRAW);
        request.setAmount(100L);
        request.setAccountFrom(from);
        request.setAccountTo(to);

        boolean withdraw = accountController.withdraw(request);

        System.out.println(withdraw);
    }

    @Test
    void remit() {
        AccountRequest request = new AccountRequest();
        AccountInfo from = new AccountInfo();
        AccountInfo to = new AccountInfo();

        from.setBankName("no bank");
        from.setBankAccountNumber("jun1");

        to.setBankName("no bank");
        to.setBankAccountNumber("225-169-673608");

        request.setAmount(1000L);
        request.setAccountFrom(from);
        request.setAccountTo(to);

        boolean remit = accountController.remit(request);

        System.out.println(remit);
    }
}
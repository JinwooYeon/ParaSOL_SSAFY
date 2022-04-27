package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.eenum.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DepositRequestFactoryTest {
    @Autowired
    private DepositRequestFactory depositRequestFactory;

    @Test
    void run() {
        DepositRequest saveInfo = new DepositRequest();
        AccountInfo from = new AccountInfo();
        AccountInfo to = new AccountInfo();

        from.setBankName("noname");
        from.setBankAccountNumber("jun1");

        to.setBankName("noname");
        to.setBankAccountNumber("sun1");

        saveInfo.setMethod(TransactionType.DEPOSIT);
        saveInfo.setAmount(50);
        saveInfo.setAccountFrom(from);
        saveInfo.setAccountTo(to);

        depositRequestFactory.run(saveInfo);
    }
}
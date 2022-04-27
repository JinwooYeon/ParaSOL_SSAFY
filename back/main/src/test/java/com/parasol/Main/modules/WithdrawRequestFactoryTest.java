package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.api_request.WithdrawRequest;
import com.parasol.Main.eenum.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WithdrawRequestFactoryTest {
    @Autowired
    private WithdrawRequestFactory withdrawRequestFactory;

    @Test
    void run() {
        WithdrawRequest saveInfo = new WithdrawRequest();
        AccountInfo from = new AccountInfo();

        from.setBankName("noname");
        from.setBankAccountNumber("sun1");

        saveInfo.setMethod(TransactionType.DEPOSIT);
        saveInfo.setAmount(1000);
        saveInfo.setAccountFrom(from);
        saveInfo.setAccountTo(from);

        withdrawRequestFactory.run(saveInfo);
    }
}
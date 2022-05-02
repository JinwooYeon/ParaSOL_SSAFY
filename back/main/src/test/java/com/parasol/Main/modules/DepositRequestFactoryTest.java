package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.eenum.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepositRequestFactoryTest {
    @Autowired
    private DepositRequestFactory depositRequestFactory;

    @Test
    void run() throws InterruptedException {
        DepositRequest saveInfo = new DepositRequest();
        AccountInfo from = new AccountInfo();
        AccountInfo to = new AccountInfo();

        from.setBankName("noname");
        from.setBankAccountNumber("188-158-441077");

        to.setBankName("noname");
        to.setBankAccountNumber("225-169-673608");

        saveInfo.setMethod(TransactionType.DEPOSIT);
        saveInfo.setAmount(50);
        saveInfo.setAccountFrom(from);
        saveInfo.setAccountTo(to);

        Mono<Boolean> result = depositRequestFactory.run(saveInfo);

        result.subscribe(res -> {
            System.out.println("response1 -> " + res);
        });

        DepositRequest saveInfo2 = new DepositRequest();
        AccountInfo from2 = new AccountInfo();
        AccountInfo to2 = new AccountInfo();

        from2.setBankName("noname");
        from2.setBankAccountNumber("225-169-000000");

        to2.setBankName("noname");
        to2.setBankAccountNumber("188-158-441077");

        saveInfo2.setMethod(TransactionType.DEPOSIT);
        saveInfo2.setAmount(10);
        saveInfo2.setAccountFrom(from2);
        saveInfo2.setAccountTo(to2);

        Mono<Boolean> result2 = depositRequestFactory.run(saveInfo2);

        result2.subscribe(res -> {
            System.out.println("response2 -> " + res);
        });

        Mono<Boolean> result3 = depositRequestFactory.run(saveInfo);

        result3.subscribe(res -> {
            System.out.println("response3 -> " + res);
        });

        Mono<Boolean> result4 = depositRequestFactory.run(saveInfo2);

        result4.subscribe(res -> {
            System.out.println("response4 -> " + res);
        });

        Thread.sleep(5000);     // 다른 일 하는 중
    }
}
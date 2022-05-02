package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.eenum.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

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

        List<Mono<Boolean>> result = new ArrayList<>();

        for(int i = 0;i < 2; i++){
            result.add(depositRequestFactory.run(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중
    }
}
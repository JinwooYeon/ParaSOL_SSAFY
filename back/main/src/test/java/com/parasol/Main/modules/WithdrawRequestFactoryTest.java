package com.parasol.Main.modules;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_request.DepositRequest;
import com.parasol.Main.api_request.WithdrawRequest;
import com.parasol.Main.eenum.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WithdrawRequestFactoryTest {
    @Autowired
    private WithdrawRequestFactory withdrawRequestFactory;

    @Test
    void run() throws InterruptedException {
        WithdrawRequest saveInfo = new WithdrawRequest();
        AccountInfo from = new AccountInfo();

        from.setBankName("noname");
        from.setBankAccountNumber("225-169-673608");

        saveInfo.setMethod(TransactionType.DEPOSIT);
        saveInfo.setAmount(1000);
        saveInfo.setAccountFrom(from);
        saveInfo.setAccountTo(from);

        List<Mono<Boolean>> result = new ArrayList<>();

        for(int i = 0;i < 2; i++){
            result.add(withdrawRequestFactory.run(saveInfo));
            result.get(i).subscribe(res -> {
                System.out.println("response -> " + res);
            });
        }

        Thread.sleep(5000);     // 다른 일 하는 중
    }
}
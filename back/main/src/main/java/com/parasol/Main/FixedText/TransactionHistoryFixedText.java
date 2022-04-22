package com.parasol.Main.FixedText;

import com.parasol.Main.api_model.Account;
import com.parasol.Main.api_model.TransactionHistory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TransactionHistoryFixedText {
    public TransactionHistory save(TransactionHistory saveInfo) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public List<TransactionHistory> findById(int id) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }

    public List<TransactionHistory> findByAccountFrom(Account account) {
        // 코어 뱅킹 시스템 rest api 호출
        return null;
    }
}

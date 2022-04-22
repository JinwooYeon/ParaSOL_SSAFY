package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import lombok.*;


@Getter
@Setter
@ToString
public class TransactionHistory {
    private Long id;

    private Long date;
    private TransactionType type;
    private Long amount;
    private String transactionAccount;

    private Account account;
}

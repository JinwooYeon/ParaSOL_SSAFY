package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import lombok.*;


@Getter
@Setter
@ToString
public class TransactionHistoryResponse {
    private long id;

    private long date;
    private TransactionType type;
    private long amount;
    private double balance;
    private String transactionAccount;

    private AccountResponse account;
}

package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Transaction {
    private TransactionType method;
    private long amount;
    private AccountInfo accountFrom;
    private AccountInfo accountTo;
}

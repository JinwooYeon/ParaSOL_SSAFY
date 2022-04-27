package com.parasol.BaaS.api_model;

import com.parasol.BaaS.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private TransactionType method;
    private Long amount;
    private AccountInfo accountFrom;
    private AccountInfo accountTo;
}

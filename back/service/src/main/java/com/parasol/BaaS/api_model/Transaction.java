package com.parasol.BaaS.api_model;

import com.parasol.BaaS.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class Transaction {
    private TransactionType method;
    private Long amount;
    private AccountInfo accountFrom;
    private AccountInfo accountTo;
}

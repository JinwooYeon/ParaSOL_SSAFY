package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
public class TransactionResponse {
    private TransactionType method;
    private long amount;
    private String accountFrom;
    private String accountTo;
}

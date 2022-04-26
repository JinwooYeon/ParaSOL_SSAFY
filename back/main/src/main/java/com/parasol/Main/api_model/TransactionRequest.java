package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class TransactionRequest {
    private TransactionType method;
    private long amount;
    private String accountFrom;
    private String accountTo;
}

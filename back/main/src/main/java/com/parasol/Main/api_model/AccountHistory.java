package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class AccountHistory {
    private LocalDateTime txDatetime;
    private TransactionType txType;
    private Long amount;
    private String accountTo;
    private Long balance;
}

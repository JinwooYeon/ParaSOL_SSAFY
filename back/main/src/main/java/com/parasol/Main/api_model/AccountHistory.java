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
public class AccountHistory {
    private LocalDateTime txDatetime;
    private TransactionType txMethod;
    private Long amount;
    private AccountInfo accountTo;
    private Long balance;
}

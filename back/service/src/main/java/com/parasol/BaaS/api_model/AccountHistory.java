package com.parasol.BaaS.api_model;

import com.parasol.BaaS.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountHistory {
    private LocalDateTime txDatetime;
    private TransactionType txMethod;
    private Long amount;
    private AccountInfo accountTo;
    private Long balance;
}

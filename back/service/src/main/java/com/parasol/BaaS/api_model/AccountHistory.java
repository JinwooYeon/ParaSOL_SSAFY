package com.parasol.BaaS.api_model;

import com.parasol.BaaS.enums.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountHistory {
    private LocalDateTime txDatetime;
    private TransactionType txMethod;
    private Long amount;
    private AccountInfo accountTo;
    private Long balance;
}

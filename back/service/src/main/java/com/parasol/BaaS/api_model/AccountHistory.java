package com.parasol.BaaS.api_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parasol.BaaS.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter

public class AccountHistory {
    @NotNull
    private Long id;
    @NotNull
    private Long date;
    @NotNull
    private TransactionType type;
    @PositiveOrZero
    private Long amount;
    @NotNull
    private AccountInfo accountTo;
    @NotBlank
    private String transactionAccount;
}

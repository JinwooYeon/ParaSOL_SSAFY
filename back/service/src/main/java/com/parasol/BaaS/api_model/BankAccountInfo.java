package com.parasol.BaaS.api_model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class BankAccountInfo {
    @NotNull
    private String bankImg;
    @NotNull
    private String bankName;
    @NotNull
    private String bankNum;
}

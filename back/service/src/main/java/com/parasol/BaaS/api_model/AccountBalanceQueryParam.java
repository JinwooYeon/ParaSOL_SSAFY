package com.parasol.BaaS.api_model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountBalanceQueryParam {
    private String accountNumber;
    private String id;
    private String password;
}

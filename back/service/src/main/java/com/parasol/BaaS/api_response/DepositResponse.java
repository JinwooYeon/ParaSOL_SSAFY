package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DepositResponse {
    private Long amount;
    private String nameFrom;
    private AccountInfo accountTo;
}

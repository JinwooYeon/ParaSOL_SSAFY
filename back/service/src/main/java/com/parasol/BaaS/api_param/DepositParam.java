package com.parasol.BaaS.api_param;

import com.parasol.BaaS.api_model.AccountInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class DepositParam {
    private Long amount;
    private String nameFrom;
    private AccountInfo accountTo;
}

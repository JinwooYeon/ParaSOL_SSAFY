package com.parasol.BaaS.api_response;

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
public class WithdrawResponse {
    private Long amount;
    private AccountInfo accountFrom;
    private String nameTo;
}

package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountBalance;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceQueryResultResponse extends AccountBalance {
    private String bankName;
    private String bankAccountNumber;
}

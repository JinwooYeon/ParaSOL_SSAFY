package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountHistories;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountHistoryQueryResultResponse extends AccountHistories {
    private String bankName;
    private String bankAccountNumber;
}

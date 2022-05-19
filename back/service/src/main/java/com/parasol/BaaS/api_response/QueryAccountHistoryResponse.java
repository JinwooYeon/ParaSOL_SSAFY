package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class QueryAccountHistoryResponse {
    private String bankName;
    private String bankAccountNumber;
    private List<AccountHistory> bankAccountHistories;
}

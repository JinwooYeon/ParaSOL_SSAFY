package com.parasol.accountHistory.api_response;

import com.parasol.accountHistory.api_model.AccountHistory;
import io.swagger.annotations.ApiModel;
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
@ApiModel("AccountHistoryResult")
public class AccountHistoryResult {
    private List<AccountHistory> accountHistories;
}

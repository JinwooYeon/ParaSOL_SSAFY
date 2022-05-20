package com.parasol.BaaS.api_result;

import com.parasol.BaaS.api_model.AccountHistory;
import com.parasol.BaaS.api_model.AccountInfo;
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
public class QueryAccountHistoryResult {
    private List<AccountHistory> accountHistories;
}

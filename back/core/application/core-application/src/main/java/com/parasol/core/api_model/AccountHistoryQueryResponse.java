package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("AccountHistoryResultResponse")
public class AccountHistoryQueryResponse {
    private List<AccountHistory> accountHistories;
}

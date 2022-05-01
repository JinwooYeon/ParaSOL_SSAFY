package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountHistoryList;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("AccountHistoriesQueryResultResponse")
public class AccountHistoriesQueryResultResponse extends AccountHistoryList {
}

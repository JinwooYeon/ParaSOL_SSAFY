package com.parasol.accountHistory.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("AccountHistoryList")
public class AccountHistoryList {
    @ApiModelProperty(name = "accountHistories", example = "")
    @NotNull
    private List<AccountHistory> accountHistories;
}

package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("AccountBalanceQueryResultResponse")
public class AccountBalanceQueryResultResponse extends AccountBalance {
}

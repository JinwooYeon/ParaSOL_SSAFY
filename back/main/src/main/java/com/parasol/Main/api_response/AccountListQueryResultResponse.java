package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountList;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("AccountListQueryResultResponse")
public class AccountListQueryResultResponse extends AccountList {
}

package com.parasol.Main.api_request;

import com.parasol.Main.api_model.AccountInfo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("AccountBalanceQueryRequest")
public class AccountBalanceQueryRequest extends AccountInfo {
}

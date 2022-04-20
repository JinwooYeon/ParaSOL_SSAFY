package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("AccountQueryRequest")
public class AccountQueryRequest extends ClientInfo{
    @ApiModelProperty(name="account_no", example = "110-437-525252")
    private String accountNo;
}

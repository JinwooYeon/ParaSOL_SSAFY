package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@ApiModel("AccountWithdrawRequest")
public class AccountWithdrawRequest extends AccountRequest {
    @ApiModelProperty(name = "account_password", example = "0809")
    @NotNull
    @Size(min = 4, max = 4)
    private String accountPassword;
}

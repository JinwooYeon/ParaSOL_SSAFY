package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel("AccountOpenRequest")
public class AccountOpenRequest extends ClientInfo {
    @ApiModelProperty(name="account_password", example = "0809")
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int accountPassword;
}

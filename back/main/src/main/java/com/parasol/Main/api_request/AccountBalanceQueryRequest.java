package com.parasol.Main.api_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@ApiModel("AccountBalanceQueryRequest")
public class AccountBalanceQueryRequest{
    @ApiModelProperty(name = "account_no", example = "110-437-525252")
    @NotBlank
    @Size(max = 14, min = 14)
    private String accountNo;

    @ApiModelProperty(name="account_password", example = "1234")
    @NotBlank
    @Size(max = 4, min = 4)
    private String accountPassword;
}

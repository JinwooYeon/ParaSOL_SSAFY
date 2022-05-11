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
@ApiModel("AccountHistoryQueryRequest")
public class AccountHistoryQueryRequest{
    @ApiModelProperty(name = "account_number", example = "110-437-525252")
    @NotBlank
    private String accountNumber;

    @ApiModelProperty(name="account_password", example = "1234")
    @NotBlank
    @Size(max = 4, min = 4)
    private String accountPassword;
}

package com.parasol.Main.api_request;

import com.parasol.Main.api_model.LoginInfo;
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
public class AccountBalanceQueryRequest extends LoginInfo {
    @ApiModelProperty(name = "account_number", example = "110-437-525252")
    @NotBlank
    @Size(max = 14, min = 14)
    private String accountNumber;
}

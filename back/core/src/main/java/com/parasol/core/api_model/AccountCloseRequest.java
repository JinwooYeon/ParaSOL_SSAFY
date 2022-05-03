package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@ApiModel("AccountCloseRequest")
public class AccountCloseRequest {
    @ApiModelProperty(name="account_no", example = "110-437-525252")
    @NotBlank
    private String accountNo;
    @ApiModelProperty(name="account_password", example = "0809")
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int accountPassword;
}

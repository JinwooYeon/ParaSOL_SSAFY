package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("AccountNumber")
public class AccountNumber {
    @ApiModelProperty(name = "account_number", example = "639")
    private String accountNumber;
}

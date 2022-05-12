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
public class AccountHistoryQueryRequest extends LoginRequest{
    @ApiModelProperty(name = "cusNo")
    private Long cusNo;

    @ApiModelProperty(name = "account_number", example = "110-437-525252")
    @NotBlank
    private String accountNumber;
}

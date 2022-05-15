package com.parasol.accountList.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel("AccountNumber")
public class AccountNumber {
    @ApiModelProperty(name = "account_number", example = "639")
    private String accountNumber;
}

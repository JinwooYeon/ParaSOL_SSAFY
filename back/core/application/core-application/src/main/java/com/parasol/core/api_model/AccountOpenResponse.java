package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AccountOpenResponse")
public class AccountOpenResponse {
    @ApiModelProperty(name = "bank_account_number", example = "639")
    @NotBlank
    private String accountNumber;
}

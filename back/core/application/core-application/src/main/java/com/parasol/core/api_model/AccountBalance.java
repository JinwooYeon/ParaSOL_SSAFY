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
@ApiModel("AccountBalance")
public class AccountBalance {
    @ApiModelProperty(name = "balance", example = "5000")
    @NotBlank
    private long balance;
}

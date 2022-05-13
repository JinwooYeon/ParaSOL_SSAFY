package com.parasol.maindeposit.api_request;

import com.parasol.maindeposit.api_model.AccountInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@ApiModel("DepositRequest")
public class DepositRequest{
    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private long amount;
    @ApiModelProperty(name = "account_to", example = "110-437-525252")
    private AccountInfo accountTo;
    @ApiModelProperty(name = "name_from", example = "")
    private String nameFrom;
}

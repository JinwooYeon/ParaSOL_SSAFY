package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("FirmbankingRegisterRequest")
public class FirmbankingRegisterRequest {
    @ApiModelProperty(name = "firmbanking_name")
    private String firmbankingName;
    @ApiModelProperty(name = "firmbanking_type")
    private String firmbankingType;
    @ApiModelProperty(name = "firmbanking_deposit_account_no")
    private String firmbankingDepositAccountNo;
    @ApiModelProperty(name = "firmbanking_withdraw_account_no")
    private String firmbankingWithdrawAccountNo;
}

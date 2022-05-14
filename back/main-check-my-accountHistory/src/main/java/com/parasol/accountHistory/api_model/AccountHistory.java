package com.parasol.accountHistory.api_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parasol.accountHistory.eenum.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@ApiModel("AccountHistory")
public class AccountHistory {
    @ApiModelProperty(name= "id", example ="2" )
    @JsonProperty("id")
    @NotNull
    private Long txId;
    @ApiModelProperty(name= "txDatetime", example ="2022-04-28 18:43:22:03" )
    @JsonProperty("date")
    @NotNull
    private Long txDatetime;
    @ApiModelProperty(name= "txMethod", example ="1" )
    @JsonProperty("type")
    @NotNull
    private TransactionType txMethod;
    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private Long amount;
    @ApiModelProperty(name = "account_to", example = "110-437-525252")
    @NotNull
    private AccountInfo accountTo;

    @ApiModelProperty(name = "transactionAccount")
    @NotBlank
    private String transactionAccount;
}

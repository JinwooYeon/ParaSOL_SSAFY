package com.parasol.core.api_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parasol.core.eenum.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AccountHistory")
public class AccountHistory {
    @ApiModelProperty(name= "id", example ="2" )
    @JsonProperty("id")
    @NotNull
    private Long id;
    @ApiModelProperty(name= "txDatetime", example ="2022-04-28 18:43:22:03" )
    @JsonProperty("date")
    @NotNull
    private Long datetime;
    @ApiModelProperty(name= "txMethod", example ="1" )
    @JsonProperty("type")
    @NotNull
    private TransactionType method;
    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private Long amount;
    @ApiModelProperty(name = "account", example = "110-437-525252")
    @NotNull
    private String account;
    @ApiModelProperty(name = "nameOpponent")
    @NotBlank
    private String nameOpponent;
}

package com.parasol.maindeposit.api_model;

import com.parasol.maindeposit.eenum.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("Transaction")
public class Transaction {
    @ApiModelProperty(name = "method", example = "0")
    @Nullable
    private TransactionType method;
    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private long amount;
    @ApiModelProperty(name = "account_from", example = "110-437-525252")
    @Nullable
    private AccountInfo accountFrom;
    @ApiModelProperty(name = "account_to", example = "110-437-525252")
    @Nullable
    private AccountInfo accountTo;
    @ApiModelProperty(name = "name_opponent", example = "")
    @Nullable
    private String nameOpponent;
}

package com.parasol.Main.api_request;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_model.Transaction;
import com.parasol.Main.eenum.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@ApiModel("DepositRequest")
public class DepositRequest extends LoginRequest {
    @ApiModelProperty(name = "cusNo")
    @NotBlank
    private Long cusNo;

    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private long amount;

    @ApiModelProperty(name = "account_to", example = "110-437-525252")
    @Nullable
    private AccountInfo accountTo;

    @ApiModelProperty(name = "name_opponent", example = "")
    @Nullable
    private String nameOpponent;
}

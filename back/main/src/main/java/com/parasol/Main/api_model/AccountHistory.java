package com.parasol.Main.api_model;

import com.parasol.Main.eenum.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@ApiModel("AccountHistory")
public class AccountHistory {
    @ApiModelProperty(name= "txDatetime", example ="2022-04-28 18:43:22:03" )
    @NotNull
    private LocalDateTime txDatetime;
    @ApiModelProperty(name= "txMethod", example ="1" )
    @NotNull
    private TransactionType txMethod;
    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private Long amount;
    @ApiModelProperty(name = "account_to", example = "110-437-525252")
    @NotNull
    private AccountInfo accountTo;
    @ApiModelProperty(name = "balance", example = "5000")
    @NotBlank
    private Long balance;
}

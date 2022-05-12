package com.parasol.Main.api_request;

import com.parasol.Main.api_model.AccountInfo;
import com.parasol.Main.api_model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("WithdrawRequest")
public class WithdrawRequest extends LoginRequest {
    @ApiModelProperty(name = "cusNo")
    @NotBlank
    private Long cusNo;

    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private long amount;

    @ApiModelProperty(name = "account_from", example = "110-437-525252")
    @Nullable
    private AccountInfo accountFrom;

    @ApiModelProperty(name = "name_opponent", example = "")
    @Nullable
    private String nameOpponent;

    @ApiModelProperty(name="account_password", example = "1234")
    @NotBlank
    @Size(max = 4, min = 4)
    private String accountPassword;
}

package com.parasol.mainwithdraw.api_request;

import com.parasol.mainwithdraw.api_model.AccountInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("WithdrawRequest")
public class WithdrawRequest {
    @ApiModelProperty(name="id", example = "nini6630")
    @NotBlank
    private String id;
    @NotBlank
    @ApiModelProperty(name="password", example = "1q2w3e4r")
    private String password;
    @ApiModelProperty(name = "amount", example = "4500000000")
    @PositiveOrZero
    private long amount;
    @ApiModelProperty(name = "account_to", example = "110-437-525252")
    private AccountInfo accountFrom;
    @ApiModelProperty(name = "name_from", example = "")
    private String nameTo;
    @ApiModelProperty(name="account_password", example = "1234")
    @NotBlank
    @Size(max = 4, min = 4)
    private String accountPassword;
}

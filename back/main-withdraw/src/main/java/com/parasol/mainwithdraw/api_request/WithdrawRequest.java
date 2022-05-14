package com.parasol.mainwithdraw.api_request;

import com.parasol.mainwithdraw.api_model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("WithdrawRequest")
public class WithdrawRequest extends Transaction {
    @ApiModelProperty(name="account_password", example = "1234")
    @NotBlank
    @Size(max = 4, min = 4)
    private String accountPassword;
}

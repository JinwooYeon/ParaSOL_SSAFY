package com.parasol.pay.api_request;

import com.parasol.pay.api_model.ClientInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@ApiModel("AccountOpenRequest")
public class AccountOpenRequest extends ClientInfo {
    @ApiModelProperty(name="account_password", example = "0809")
    @NotNull
    @Size(min = 4, max = 4)
    private String accountPassword;
}

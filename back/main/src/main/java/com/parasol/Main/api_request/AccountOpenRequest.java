package com.parasol.Main.api_request;

import com.parasol.Main.api_model.Client;
import com.parasol.Main.api_model.ClientInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@ApiModel("AccountOpenRequest")
public class AccountOpenRequest{
    @NotBlank
    private String id;

    @ApiModelProperty(name="account_password", example = "0809")
    @NotNull
    @Size(min = 4, max = 4)
    private String accountPassword;
}

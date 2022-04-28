package com.parasol.Main.api_request;

import com.parasol.Main.api_model.ClientInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Builder
@ApiModel("AccountOpenRequest")
public class AccountOpenRequest extends ClientInfo {
    @ApiModelProperty(name="account_password", example = "0809")
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int accountPassword;
}

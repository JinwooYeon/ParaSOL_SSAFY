package com.parasol.Main.api_response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@ApiModel("LoginResponse")
@Builder
public class LoginResponse {
    @ApiModelProperty(name="isSuccess",example = "")
    Boolean isSuccess;
}

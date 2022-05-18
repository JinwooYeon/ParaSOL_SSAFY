package com.parasol.login.api_response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@ApiModel("LoginResultResponse")
@Builder
public class LoginResultResponse {
    @ApiModelProperty(name="isSuccess",example = "")
    Boolean isSuccess;
}

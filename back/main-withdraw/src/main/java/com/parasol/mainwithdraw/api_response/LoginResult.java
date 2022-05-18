package com.parasol.mainwithdraw.api_response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("LoginResult")
@Builder
public class LoginResult {
    @ApiModelProperty(name="isSuccess",example = "")
    Boolean isSuccess;

    @ApiModelProperty(name="cusNo",example = "")
    Long cusNo;
}

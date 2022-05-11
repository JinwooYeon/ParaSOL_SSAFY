package com.parasol.Main.api_response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@ApiModel("ClientResponse")
@Builder
public class LoginResultResponse {
    @ApiModelProperty(name="isSuccess",example = "")
    Boolean isSuccess;

    @ApiModelProperty(name="cusNo",example = "")
    String cusNo;
}

package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("AuthToken")
public class AuthToken {
    @ApiModelProperty(name = "accessToken", example = "")
    AccessToken accessToken;
    @ApiModelProperty(name = "refreshToken", example = "")
    RefreshToken refreshToken;
}

package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@ApiModel("AccessToken")
public class AccessToken {
    @ApiModelProperty(name = "accessToken", example = "")
    @NotNull
    private String accessToken;
    @ApiModelProperty(name = "accessTokenExpiredAt", example = "2022-04-28 18:43:22")
    @NotNull
    private Timestamp accessTokenExpiredAt;
}

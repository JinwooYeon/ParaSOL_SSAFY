package com.parasol.pay.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("LoginInfo")
@SuperBuilder
@NoArgsConstructor
public class LoginInfo {
    @ApiModelProperty(name="id", example = "nini6630")
    @NotBlank
    private String id;
    @NotBlank
    @ApiModelProperty(name="password", example = "1q2w3e4r")
    private String password;
}

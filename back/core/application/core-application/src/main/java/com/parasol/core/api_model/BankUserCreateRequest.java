package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@ApiModel("BankUserCreateRequest")
public class BankUserCreateRequest {
    @ApiModelProperty(name="name", example = "김형준")
    @NotBlank
    private String name;
    @ApiModelProperty(name="residentNumber", example = "961234-1234567")
    @NotBlank
    @Size(max = 14, min = 14)
    private String residentNumber;
    @ApiModelProperty(name="id", example = "parasol")
    @NotBlank
    //@Size(max = 5, min = 20)
    private String id;
    @ApiModelProperty(name="password", example = "parasol!@#$")
    @NotBlank
    //@Size(max = 8, min = 32)
    private String password;
}

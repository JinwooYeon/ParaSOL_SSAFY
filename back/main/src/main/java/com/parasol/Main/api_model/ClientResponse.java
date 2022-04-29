package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("ClientResponse")
public class ClientResponse {
    @ApiModelProperty(name="id",example = "")
    private String id;
    @ApiModelProperty(name="name",example = "")
    @NotBlank
    private String name;
    @ApiModelProperty(name="residentNumber",example = "")
    @NotBlank
    private String residentNumber;
};

package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("ClientId")
public class ClientId {
    @ApiModelProperty(name="id", example = "nini6630")
    @NotBlank
    private String id;
}

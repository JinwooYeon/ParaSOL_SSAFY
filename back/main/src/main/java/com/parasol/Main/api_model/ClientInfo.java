package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("ClientInfo")
public class ClientInfo {
    @ApiModelProperty(name="name", example="김싸피")
    @NotBlank
    private String name;
    @ApiModelProperty(name="id", example="5124dd4684")
    @Nullable
    private String id;
}

package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("Client")
public class Client {
    @ApiModelProperty(name = "id")
    private String id;

    @NotBlank
    @ApiModelProperty(name = "name")
    private String name;
    @NotBlank
    @ApiModelProperty(name = "residentNumber")
    private String residentNumber;
};

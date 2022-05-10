package com.parasol.core.api_model;

import com.parasol.core.entity.BankUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("ClientInfo")
public class ClientInfo {
    @ApiModelProperty(name="name", example="김싸피")
    @NotBlank
    private String name;
    @Nullable
    private BankUser bankUser;
    @Nullable
    private String id;

}

package com.parasol.Main.api_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("AccountListQueryRequest")
public class AccountListQueryRequest{
    @ApiModelProperty(name = "id")
    @NotBlank
    private String id;
}

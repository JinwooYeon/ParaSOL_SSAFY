package com.parasol.Main.api_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("AccountListQueryRequest")
public class AccountListQueryRequest extends LoginRequest{
    @ApiModelProperty(name = "cusNo")
    private Long cusNo;
}

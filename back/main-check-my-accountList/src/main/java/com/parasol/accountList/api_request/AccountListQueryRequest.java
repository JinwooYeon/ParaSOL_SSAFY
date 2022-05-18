package com.parasol.accountList.api_request;

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
    @ApiModelProperty(name="id", example = "nini6630")
    @NotBlank
    private String id;
    @NotBlank
    @ApiModelProperty(name="password", example = "1q2w3e4r")
    private String password;
}

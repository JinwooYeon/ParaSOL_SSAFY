package com.parasol.Main.api_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@ApiModel("AccountListQueryParam")
public class AccountListQueryParam {
    @ApiModelProperty(name="cusNo", example = "1")
    @NotBlank
    private Long cusNo;
}

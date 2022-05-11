package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountBalance {
    @ApiModelProperty(name = "totalBalance", example = "5000")
    @NotBlank
    private long totalBalance;

    @ApiModelProperty(name = "availableBalance", example = "5000")
    @NotBlank
    private long availableBalance;
}

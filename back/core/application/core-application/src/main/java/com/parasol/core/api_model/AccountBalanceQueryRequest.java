package com.parasol.core.api_model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceQueryRequest {
    @ApiModelProperty(name="account_number", example = "110-437-525252")
    @NotBlank
    @Size(max = 14, min = 14)
    private String accountNumber;
}

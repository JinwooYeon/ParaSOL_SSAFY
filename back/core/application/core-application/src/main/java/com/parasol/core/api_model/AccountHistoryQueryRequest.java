package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
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
@ApiModel("AccountHistoryQueryRequest")
public class AccountHistoryQueryRequest {
    private Long cusNo;
    @ApiModelProperty(name="account_number", example = "110-437-525252")
    @NotBlank
    @Size(max = 14, min = 14)
    private String accountNumber;

//    @ApiModelProperty(name="account_password", example = "1234")
//    @NotBlank
//    @Size(max = 4, min = 4)
//    private String accountPassword;
}

package com.parasol.pay.api_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("AccountBalanceQueryParam")
public class AccountBalanceQueryParam {
        @ApiModelProperty(name="cusNo", example = "1")
        @NotBlank
        private Long cusNo;
        @ApiModelProperty(name = "account_number", example = "110-437-525252")
        @NotBlank
        @Size(max = 14, min = 14)
        private String accountNumber;

}

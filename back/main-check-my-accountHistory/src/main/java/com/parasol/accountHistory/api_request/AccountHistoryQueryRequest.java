package com.parasol.accountHistory.api_request;

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
@ApiModel("AccountHistoryQueryRequest")
public class AccountHistoryQueryRequest {
    @ApiModelProperty(name = "account_number", example = "110-437-525252")
    @NotBlank
    @Size(max = 14, min = 14)
    private String accountNumber;

    @ApiModelProperty(name="id", example = "nini6630")
    @NotBlank
    private String id;
    @NotBlank
    @ApiModelProperty(name="password", example = "1q2w3e4r")
    private String password;
}

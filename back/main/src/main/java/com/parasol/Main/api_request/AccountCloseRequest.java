package com.parasol.Main.api_request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@Builder
@ApiModel("AccountCloseRequest")
public class AccountCloseRequest {
    @ApiModelProperty(name="account_number", example = "110-437-525252")
    @NotBlank
    private String accountNumber;
    @ApiModelProperty(name="resident_number", example = "000000-0000000")
    @NotBlank
    @Size(max=14, min=14)
    private String residentNumber;
    @ApiModelProperty(name="account_password", example = "0809")
    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int accountPassword;
}

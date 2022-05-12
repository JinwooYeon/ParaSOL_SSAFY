package com.parasol.Main.api_response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("BankUserCreateResponse")
public class BankUserCreateResponse {
    private String id;
}

package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("AccountList")
public class AccountList {
    @ApiModelProperty(name = "accounts", example ="")
    @NotNull
    private List<AccountInfo> accounts;
}

package com.parasol.Main.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@ApiModel("CompanyInfo")
public class CompanyInfo {
    @ApiModelProperty(name="company_name",example = "토스")
    @NotBlank
    private String companyName;
}

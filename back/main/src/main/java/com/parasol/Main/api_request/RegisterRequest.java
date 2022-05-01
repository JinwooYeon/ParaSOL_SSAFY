package com.parasol.Main.api_request;

import com.parasol.Main.api_model.CompanyInfo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("RegisterRequest")
public class RegisterRequest extends CompanyInfo {
}

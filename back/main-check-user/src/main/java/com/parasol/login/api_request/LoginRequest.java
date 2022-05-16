package com.parasol.login.api_request;

import com.parasol.login.api_model.LoginInfo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("LoginRequest")
public class LoginRequest extends LoginInfo {
}

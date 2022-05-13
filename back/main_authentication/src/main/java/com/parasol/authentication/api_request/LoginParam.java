package com.parasol.authentication.api_request;

import com.parasol.authentication.api_model.LoginInfo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@ApiModel("LoginParam")
@SuperBuilder
@NoArgsConstructor
public class LoginParam extends LoginInfo {
}

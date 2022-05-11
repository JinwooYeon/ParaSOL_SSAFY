package com.parasol.Main.api_request;

import com.parasol.Main.api_model.LoginInfo;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("LoginRequest")
public class LoginRequest extends LoginInfo {
}

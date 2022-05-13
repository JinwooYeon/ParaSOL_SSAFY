package com.parasol.Main.api_request;

import com.parasol.Main.api_model.LoginInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("AccountListQueryRequest")
public class AccountListQueryRequest extends LoginInfo {
}

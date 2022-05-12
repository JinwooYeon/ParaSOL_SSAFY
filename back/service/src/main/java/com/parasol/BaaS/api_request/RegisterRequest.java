package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RegisterRequest extends UserInfo {
}

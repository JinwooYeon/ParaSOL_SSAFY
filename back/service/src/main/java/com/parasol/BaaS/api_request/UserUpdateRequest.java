package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class UserUpdateRequest extends UserInfo {
}

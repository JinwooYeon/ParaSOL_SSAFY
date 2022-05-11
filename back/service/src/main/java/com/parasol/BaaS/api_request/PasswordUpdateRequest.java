package com.parasol.BaaS.api_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class PasswordUpdateRequest {
    private String password;
    private String newPassword;
}

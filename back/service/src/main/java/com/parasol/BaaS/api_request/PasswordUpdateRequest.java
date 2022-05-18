package com.parasol.BaaS.api_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class PasswordUpdateRequest {
    private Authentication authentication;
    private String password;
    private String newPassword;
}

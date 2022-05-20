package com.parasol.BaaS.api_request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class UpdateRequest {
    private Authentication authentication;
    private String password;
    private String newPassword;
}
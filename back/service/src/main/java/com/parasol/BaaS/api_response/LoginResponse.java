package com.parasol.BaaS.api_response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
}

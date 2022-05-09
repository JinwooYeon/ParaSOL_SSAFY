package com.parasol.BaaS.api_response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthTokenResponse {
    private String accessToken;
    private String refreshToken;
}

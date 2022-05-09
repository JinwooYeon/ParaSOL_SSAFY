package com.parasol.BaaS.api_model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    private AccessToken accessToken;
    private RefreshToken refreshToken;
}

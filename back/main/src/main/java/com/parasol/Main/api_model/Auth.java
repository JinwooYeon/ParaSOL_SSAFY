package com.parasol.Main.api_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Auth {
    // Todo : Validation
    private String id;  // UUID
    private String apiKey;
    private String refreshToken;
    private Timestamp refreshTokenExpiredAt;
    private String accessToken;
    private String accessTokenExpiredAt;
}

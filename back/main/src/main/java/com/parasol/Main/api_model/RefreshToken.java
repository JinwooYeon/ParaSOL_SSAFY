package com.parasol.Main.api_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class RefreshToken {
    private String refreshToken;
    private Timestamp accessTokenExpiredAt;
}

package com.parasol.BaaS.api_result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OAuthLoginResult {
    @JsonProperty(value="access_token")
    private String accessToken;
    @JsonProperty(value="expires_in")
    private String expiresIn;
    @JsonProperty(value="refresh_token")
    private String refreshToken;
    @JsonProperty(value="scope")
    private String scope;
    @JsonProperty(value="token_type")
    private String tokenType;
    @JsonProperty(value="id_token")
    private String idToken;
}

package com.parasol.BaaS.api_param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OAuthLoginParam {
    @JsonProperty(value="code")
    private String code;
    @JsonProperty(value="client_id")
    private String clientId;
    @JsonProperty(value="client_secret")
    private String clientSecret;
    @JsonProperty(value="redirect_uri")
    private String redirectUri;
    @JsonProperty(value="grant_type")
    private String grantType;
}

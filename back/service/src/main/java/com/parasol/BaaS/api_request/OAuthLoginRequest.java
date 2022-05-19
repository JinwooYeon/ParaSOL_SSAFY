package com.parasol.BaaS.api_request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OAuthLoginRequest {
    private String state;
    private String code;
    private String scope;
    private String authuser;
    private String prompt;
    private String redirectUri;
}

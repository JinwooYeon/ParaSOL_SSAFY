package com.parasol.BaaS.api_result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GooglePayload {
    private String iss;
    private String azp;
    private String aud;
    private String sub;
    private String email;
    @JsonProperty(value="email_verified")
    private String emailVerified;
    @JsonProperty(value="at_hash")
    private String atHash;
    private String name;
    private String picture;
    @JsonProperty(value="given_name")
    private String givenName;
    @JsonProperty(value="family_name")
    private String familyName;
    private String locale;
    private String iat;
    private String exp;
}

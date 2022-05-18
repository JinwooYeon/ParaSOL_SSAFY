package com.parasol.BaaS.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PayQueryTwoFactorResponse {
    @JsonProperty(value="otp")
    private boolean otp;

    @JsonProperty(value="bio")
    private boolean bio;
}

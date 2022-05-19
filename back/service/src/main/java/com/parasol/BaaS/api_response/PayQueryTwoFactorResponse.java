package com.parasol.BaaS.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayQueryTwoFactorResponse {
    @JsonProperty(value="otp")
    private boolean otp;

    @JsonProperty(value="bio")
    private boolean bio;
}

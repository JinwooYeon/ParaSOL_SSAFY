package com.parasol.BaaS.api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayDeleteBioResponse {
    @JsonProperty(value="isSuccess")
    private boolean isSuccess;
}

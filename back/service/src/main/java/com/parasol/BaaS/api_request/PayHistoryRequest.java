package com.parasol.BaaS.api_request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayHistoryRequest {
    private Authentication authentication;
    private String month;
}

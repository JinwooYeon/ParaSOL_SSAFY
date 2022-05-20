package com.parasol.BaaS.api_response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayTransactionResponse {
    private String balance;
}

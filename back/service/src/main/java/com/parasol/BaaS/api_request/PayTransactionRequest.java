package com.parasol.BaaS.api_request;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayTransactionRequest {
    private Authentication authentication;
    private String method;
    private Long price;
    private String transactionTo;
}

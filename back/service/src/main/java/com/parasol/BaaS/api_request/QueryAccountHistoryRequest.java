package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.AccountInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class QueryAccountHistoryRequest {
    private Authentication authentication;
    private String bankName;
    private String bankAccountNumber;
}

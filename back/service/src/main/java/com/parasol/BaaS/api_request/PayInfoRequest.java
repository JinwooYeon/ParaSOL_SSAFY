package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.LoginInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayInfoRequest {
    private Authentication authentication;
}

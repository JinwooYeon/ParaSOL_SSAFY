package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.RefreshToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ReissueTokenRequest {
    private Authentication authentication;
}

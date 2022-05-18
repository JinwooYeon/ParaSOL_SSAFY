package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class QueryUserInfoRequest {
    private Authentication authentication;
}

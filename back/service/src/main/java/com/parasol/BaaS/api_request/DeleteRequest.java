package com.parasol.BaaS.api_request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class DeleteRequest {
    private Authentication authentication;
}

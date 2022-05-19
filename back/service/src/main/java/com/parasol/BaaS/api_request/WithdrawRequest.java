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
public class WithdrawRequest {
    private Authentication authentication;
    private String bankName;
    private String bankAccountPassword;
    private Long amount;
    private AccountInfo accountFrom;
    private String nameTo;
}

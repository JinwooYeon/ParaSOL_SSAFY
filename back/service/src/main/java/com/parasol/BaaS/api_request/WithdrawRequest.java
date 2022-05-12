package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {
    private Authentication authentication;
    private String bankName;
    private String bankAccountPassword;
    private Long amount;
    private AccountInfo accountTo;
    private String nameFrom;
}

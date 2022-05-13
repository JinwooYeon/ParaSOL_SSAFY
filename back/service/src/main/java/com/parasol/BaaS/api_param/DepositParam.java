package com.parasol.BaaS.api_param;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.LoginInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DepositParam {
    private Long amount;
    private String nameOpponent;
    private AccountInfo accountTo;
}

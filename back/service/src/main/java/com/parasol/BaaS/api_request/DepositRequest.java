package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.Transaction;
import com.parasol.BaaS.enums.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class DepositRequest {
    private String bankName;
    private Long amount;
    private String nameFrom;
    private AccountInfo accountTo;
}

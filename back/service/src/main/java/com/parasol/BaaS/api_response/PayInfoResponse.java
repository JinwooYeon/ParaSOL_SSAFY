package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.BankAccountInfo;
import com.parasol.BaaS.api_model.BankInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayInfoResponse {
    private String balance;
    private String id;
    private BankInfo bankInfo;
}

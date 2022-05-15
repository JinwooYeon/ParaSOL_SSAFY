package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.BankAccountInfo;
import com.parasol.BaaS.api_model.BankInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PayInfoResponse {
    private Long balance;
    private String id;
    private BankInfo bankInfo;
}

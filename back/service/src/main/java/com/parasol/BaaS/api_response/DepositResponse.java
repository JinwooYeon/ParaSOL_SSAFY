package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class DepositResponse {
    private Long amount;
    private String nameFrom;
    private AccountInfo accountTo;
}

package com.parasol.BaaS.api_result;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class BankLoginResult {

    private Boolean isSuccess;

}
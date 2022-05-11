package com.parasol.BaaS.api_response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankLoginResultResponse {

    private Boolean isSuccess;

    private String cusNo;

}

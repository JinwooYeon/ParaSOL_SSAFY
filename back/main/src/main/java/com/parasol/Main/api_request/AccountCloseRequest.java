package com.parasol.Main.api_request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AccountCloseRequest {
    private String accountNumber;
    private String residentNumber;
    private int accountPassword;
}

package com.parasol.Main.api_request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AccountOpenRequest {
    private String name;
    private String residentNumber;
    private int accountPassword;
}

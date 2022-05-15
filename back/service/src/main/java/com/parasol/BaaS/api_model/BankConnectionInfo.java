package com.parasol.BaaS.api_model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankConnectionInfo {
    private String bankName;
    private String id;
    private String password;
}

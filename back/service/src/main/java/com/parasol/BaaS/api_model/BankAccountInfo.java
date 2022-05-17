package com.parasol.BaaS.api_model;

import javax.validation.constraints.NotNull;

public class BankAccountInfo {
    @NotNull
    private String bankImg;
    @NotNull
    private String bankName;
    @NotNull
    private String bankNum;
}

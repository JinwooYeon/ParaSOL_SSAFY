package com.parasol.BaaS.api_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankInfo {
    private String bankImg;
    private String bankName;
    private String bankNum;
}

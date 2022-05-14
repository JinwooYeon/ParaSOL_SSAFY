package com.parasol.pay.api_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepInpt {
    private String dep_trx_biz_d;
    private String dep_trx_dtl_d;
    private String cusno;
    private String dep_acno;
    // ...
}
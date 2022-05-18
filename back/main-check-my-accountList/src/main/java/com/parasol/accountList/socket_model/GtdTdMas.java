package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GtdTdMas { // 수신거치식계좌원장
    private String dep_acno;
    private String fst_new_dt;
    private String fst_due_dt;
    private Number fst_ctrt_amt;
}

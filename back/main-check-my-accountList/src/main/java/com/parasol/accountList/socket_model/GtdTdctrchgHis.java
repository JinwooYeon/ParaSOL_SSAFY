package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GtdTdctrchgHis { // 거치식계약변경내역
    private String dep_acno;
    private String ttype_new_dt;
    private String ttype_due_dt;
    private String dep_due_rnw_t;
    private String ctrt_trm_d;
    private Long ctrt_trm_cnt;
    private Long ctrt_trm_dcnt;
    private Number apl_intrt;
}

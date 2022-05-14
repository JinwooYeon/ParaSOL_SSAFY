package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GtdIsctrchgHis { // 적립식계약변경내역
    private String itype_due_dt;
    private Number due_expt_amt;
    private String ctrt_trm_d;
    private Long ctrt_trm_cnt;
    private Long ctrt_trm_dcnt;
    private String itype_payt_meth;
    private Long itype_payt_prd;
    private String et_payt_dd;
    private Number et_payt_amt;
    private Number apl_intrt;
}

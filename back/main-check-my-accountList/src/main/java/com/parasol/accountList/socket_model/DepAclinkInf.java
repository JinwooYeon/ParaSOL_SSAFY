package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepAclinkInf { // 계좌연결정보
    private String ac_connt_k;
    private String connt_acno;
    private String connt_ccy_c;
    private Number coltr_amt;
    private Long ac_trmn_rstrt_yn;
    private String regis_trx_dt;
    private Long regis_trx_brno;
    private String rls_trx_dt;
    private Long rls_trx_brno;
}

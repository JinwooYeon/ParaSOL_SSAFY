package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepAcMas { // 수신계좌원장
    private String dep_acno;
    private String dep_ac_s;
    private Long cusno;
    private Long mng_brno;
    private String dep_ac_t;
    private String dep_sjt_class;
    private String ac_sjt_c;
    private String lcl_ac_no;
    private String prdt_c;
    private String dep_ac_new_dt;
    private String dep_ac_trmn_dt;
    private String last_rnp_trx_dt;
    private String last_cus_trx_dt;
    private Number dep_ac_blc;
    private Number pabl_blc;
    private Number pmt_rstrt_amt;
    private String ccy_c;
    private Long bbk_issu_odr;
    private String last_bbk_issu_dt;
    private String last_bbk_deed_no;
}

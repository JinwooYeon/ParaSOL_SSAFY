package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grid { //그리드01
    private String dep_acno;
    private String lcl_ac_no;
    private String gisan_dt;
    private String trx_dt;
    private Long dep_trx_his_no;
    private String dpst_pn_nm;
    private Number cr_amt;
    private Number dr_amt;
    private String bbk_smry;
    private Long bbk_bk_yn;
    private String dep_trx_crt_canc_d;
    private Number dep_ac_ledg_trx_bef_blc;
    private Number dep_ac_ledg_trx_aft_blc;
    private Long trx_brno;
    private String rcv_nm;
    private String dep_trx_biz_d;
    private String dep_trx_d;
    private Number rx_exrt;
    private String trx_exrt_c;
    private String trx_ccy_c;
}

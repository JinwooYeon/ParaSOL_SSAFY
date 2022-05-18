package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepInpt { // 수신업무공통부
    private String dep_trx_biz_d;
    private String dep_trx_dtl_d;
    private String cusno;
    private String prdt_c;
    private String mng_brno;
    private String dep_ac_t;
    private String dep_sjt_class;
    private String dep_acno;
    private String lcl_ac_no;
    private String inq_st_dt;
    private String inq_close_dt;
}
package com.parasol.accountList.socket_model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GtdIsMas { // 수신적립식계좌원장
    private String dep_acno;
    private String ti_bot_itype_payt_dt;
    private Long tot_payt_cnt;
    private Long mng_brtot_payt_agrmt_cntno;
}

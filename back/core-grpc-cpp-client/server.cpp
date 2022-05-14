#include <iostream>
#include <cstring>
#include <sys/socket.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <signal.h>

#include <grpc++/grpc++.h>

#include "core_api.pb.h"
#include "core_api.grpc.pb.h"

#define BUF_SIZE 256

struct stdheader {
    char msg_len[8];
    char trx_id[32];
    char recv_svc_c[12];
    char snd_svc_c[12];
    char rqst_resp_g[1];
    char chan_u[2];
    char client_ip_no[16];
    char client_max[12];
    char in_msg_dttm[16];
    char out_msg_dttm[16];
    char filter[1];
}

struct dataheader {
    char data_type[2]; 
    char data_len[8];
    char trx_code[12];
}

struct err_msg_out {
    struct stdheader stdheader;
    char data_type[2];
    char data_len[8];
    char trx_code[12];
    char c_db_code[12];
    char c_db_msg[300];
    char add_message[300];
}

struct depinpt {
    char dep_trx_biz_d[10];
    char dep_trx_dtl_d[10];
    char cusno[10];
    char prdt_c[10];
    char mng_brno[4];
    char dep_ac_t[10];
    char dep_sjt_class[10];
    char dep_acno[12];
    char lcl_ac_no[20];
    char inq_st_dt[8];
    char inq_close_dt[8];
}

struct grid01 {
    char dep_acno[12];
    char lcl_ac_no[20];
    char dep_ac_s[10];
    char cusno[10];
    char dep_ac_new_dt[8];
    char dep_ac_trmn_dt[8];
    char ccy_c[3];
    char dep_ac_blc[22];
    char pabl_blc[22];
    char dep_sjt_class[10];
    char prdt_c[10];
    char due_dt[8];
    char last_cus_trx_dt[8];
    char apl_intrt[12];
    char lcl_prdt_nm[200];
    char lcl_br_nm[100];
    char cashcd_iss_dt[8];
    char dep_due_rnw_t[10];
}

struct dep_ac_mas {
    char dep_acno[12];
    char dep_ac_s[10];
    char cusno[10];
    char mng_brno[4];
    char dep_ac_t[10];
    char dep_sjt_class[10];
    char ac_sjt_c[3];
    char lcl_ac_no[20];
    char prdt_c[10];
    char dep_ac_new_dt[8];
    char dep_ac_trmn_dt[8];
    char last_rnp_trx_dt[8];
    char last_cus_trx_dt[8];
    char dep_ac_blc[22];
    char pabl_blc[22];
    char pmt_rstrt_amt[22];
    char ccy_c[3];
    char bbk_issu_odr[5];
    char last_bbk_issu_dt[8];
    char last_bbk_deed_no[20];
}

struct gtd_is_mas {
    char dep_acno[12];
    char ti_bot_itype_payt_dt[8];
    char tot_payt_cnt[5];
    char tot_payt_agrmt_cnt[5];
}

struct gtd_td_mas {
    char dep_acno[12];
    char fst_new_dt[8];
    char fst_due_dt[8];
    char fst_ctrt_amt[22];
}

struct mgcbbrcode {
    char lcl_br_nm[100];
}

struct gtd_isctrchg_his {
    char itype_due_dt[8];
    char due_expt_amt[22];
    char ctrt_trm_d[10];
    char ctrt_trm_cnt[5];
    char ctrt_trm_dcnt[5];
    char itype_payt_meth[10];
    char itype_payt_prd[3];
    char et_payt_dd[2];
    char et_payt_amt[22];
    char apl_intrt[12];
}

struct gtd_tdctrchg_his {
    char dep_acno[12];
    char ttype_new_dt[8];
    char ttype_due_dt[8];
    char dep_due_rnw_t[10];
    char ctrt_trm_d[10];
    char ctrt_trm_cnt[5];
    char ctrt_trm_dcnt[5];
    char apl_intrt[12];
}

struct dep_aclink_inf {
    char ac_connt_k[10]; 
    char connt_acno[12];
    char connt_ccy_c[3];
    char coltr_amt[22];
    char ac_trmn_rstrt_yn[1];
    char regis_trx_dt[8];
    char regis_trx_brno[4];
    char rls_trx_dt[8];
    char rls_trx_brno[4];
}

struct deprnpinpt {
    char dep_trx_obj_c[10];
    char lkg_opst_refn_no[30];
    char lkg_opst_cus_nm[300];
    char dpst_pn_nm[300];
    char rcv_nm[300];
    char bbk_bk_trx_c[10];
    char bbk_smry[20];
    char dep_stmt_t[2];
    char dep_trx_memo_ctt1[400];
    char dep_trx_memo_ctt2[400];
}

struct sdep0210a_in {
    struct stdheader stdheader;
    struct dataheader dataheader;
    struct depinpt depinpt;
    char dep_ac_s[10];
    char cus_t[3];
    char dep_due_rnw_t[10];
}

struct sdep0210a_out {
    char grid_cnt_01[5];
    struct grid01 sdep0210a_out_sub01;
    char cus_snm_nm[300];
}

struct sdep0240a_in {
    struct stdheader stdheader;
    struct dataheader dataheader;
    struct depinpt depinpt;
    char dep_acno_inq_condt_d[2];
    char dep_acno_inq_t[2];
    char prdt_mng_trx_d[2];
    char trx_d[2];
}

struct sdep0240a_out {
    struct dep_ac_mas dep_ac_mas_sub01;
    struct gtd_is_mas gtd_is_mas_sub01;
    struct gtd_td_mas gtd_td_mas_sub01;
    struct mgcbbrcode gdepiqaccbna_out_sub04;
    struct gtd_isctrchg_his gtd_isctrchg_his_sub01;
    struct gtd_tdctrchg_his gtd_tdctrchg_his_sub01;
    char grid_cnt_00[5];
    struct dep_aclink_inf dep_aclink_inf_sub01;
    char cus_snm_nm[120];
    char cus_engstc_nm[100];
    char cus_t[3];
    char cus_natnlt_nat_c[3];
    char tax_c[200];
    char prdt_nm[200];
    char due_int_amt[22];
    char due_aft_int_amt[22];
    char tot_tax_amt[22];
}

struct sdep0242a_in {
    struct stdheader stdheader;
    struct dataheader dataheader;
    struct depinpt depinpt;
    char trx_amt[22];
    char dep_ac_s[10];
    char dep_trx_his_no[10];
    char lkg_yn[1];
    char lkg_ser[10];
    char dep_trx_s[10];
    char dep_trx_crt_canc_d[10];
}

struct sdep0242a_out {
    struct stdheader stdheader;
    char grid_cnt_01[5];
    struct grid01 sdep0242a_out_sub01; // 이상해씨
}

struct scus0001a_in {
    char intnbk_user_id[10];
    char intndk_pwd[24];
};

struct scus0001a_out {
    char rst_yn[1];
    char cusno[10];
};

struct sdep0970a_in {
    struct stdheader stdheader;
    struct dataheader dataheader;
    struct depinpt depinpt;
    struct deprnpinpt deprnpinpt;
    char dep_ac_ccy_c[3];
    char dep_ac_ledg_trx_amt[22];
    char apl_exrt[14];
    char cvs_ccy_c[3];
    char exrt_cvs_amt[22];
}

struct sdep0970a_out {
    struct stdheader stdheader;
    char trx_id[32];
    char dep_trx_his_no[10];
    char dep_ac_ccy_c[3];
    char dep_ac_ledg_trx_bef_blc[22];
    char dep_ac_ledg_trx_aft_blc[22];
    char dep_ac_ledg_trx_amt[22];
    char dep_acno[12];
    char lcl_ac_no[20];
    char apl_exrt[14];
    char cvs_ccy_c[3];
    char exrt_cvs_amt[22];
}

using grpc::Channel;
using grpc::ClientContext;
using grpc::Status;
using core_api::CoreAPI;
using core_api::AccountBalanceQueryGrpcRequest;
using core_api::AccountBalanceQueryGrpcResponse;
using core_api::LoginGrpcRequest;
using core_api::LoginGrpcResponse;

class CoreAPIClient {
    public:
    CoreAPIClient(std::shared_ptr<Channel> channel) : stub_(CoreAPI::NewStub(channel)) {}

    std::string getBalance(const std::string& account_number) {
        AccountBalanceQueryGrpcRequest request;
        request.set_accountnumber(account_number);

        AccountBalanceQueryGrpcResponse response;
        ClientContext context;

        Status status = stub_->getBalance(&context, request, &response);
        if (status.ok()) {
            return response.balance();
        } else {
            return "RPC Failed";
        }
    }

    struct scus0001a_out* login(const struct scus0001a_in raw_request) {
        LoginGrpcRequest request;
        request.set_id(raw_request.intnbk_user_id);
        request.set_password(raw_request.intndk_pwd);

        LoginGrpcResponse response;
        ClientContext context;

        Status status = stub_->login(&context, request, &response);

        struct scus0001a_out* raw_response = (struct scus0001a_out*)malloc(sizeof(struct scus0001a_out));
        memset(raw_response, 0, sizeof(struct scus0001a_out));

        memcpy(&raw_response->rst_yn, response.is_success().c_str(), response.is_success().size());
        memcpy(&raw_response->cusno, response.cusno().c_str(), response.cusno().size());

	//std::cout << "status: " << status.ok() << "\n";
	//std::cout << "response.is_success: " << response.is_success() << "\n";
	//std::cout << "response.cusno: " << response.cusno() << "\n";
	//std::cout << "raw_response->rst_yn: " << raw_response->rst_yn << "\n";
	//std::cout << "raw_response->cusno: " << raw_response->cusno << "\n";

        if (status.ok()) {
            return raw_response;
        } else {
            return NULL;
        }
    }

    private:
    std::unique_ptr<CoreAPI::Stub> stub_;
};

int main(int argc, char **argv) {
    std::string baseURL = "172.24.176.1:9090";

    int serv_sock;
    int clnt_sock;
    pid_t pid;
    
    struct sockaddr_in serv_addr;

    int port = 9000;
    
    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    if (serv_sock == -1) {
	    std::cout << "socket() error" << "\n";
	    exit(1);
    }

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(port);
    
    if (bind(serv_sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) == -1) {
	    std::cout << "bind() error" << "\n";
	    exit(1);
    }
    
    if (listen(serv_sock, 5) == -1) {
	    std::cout << "listen() error" << "\n";
	    exit(1);
    }
    
    struct sockaddr_in clnt_addr;
    socklen_t clnt_addr_size = sizeof(clnt_addr);
    
    while ((clnt_sock = accept(serv_sock, (struct sockaddr*)&clnt_addr, &clnt_addr_size)) >= 0) {
	    if (clnt_sock < 0) {
		    std::cout << "accept() error" << "\n";
		    exit(1);
	    }

        pid = fork();
        if (pid) {
            close(clnt_sock);
        } else {
            close(serv_sock);

            CoreAPIClient client = grpc::CreateChannel(baseURL, grpc::InsecureChannelCredentials());
    
            char sock_buf[BUF_SIZE];
            int payload_len = 0;

            while((payload_len = read(clnt_sock, &sock_buf, BUF_SIZE)) != 0) {
                int status = 200;

                std::cout << "payload_len: " << payload_len << "\n";
                std::cout << "sock_buf: " << sock_buf << "\n";

                if (payload_len == sizeof(struct scus0001a_in)) {
                     struct scus0001a_in req_buf;
                     memcpy(&req_buf, sock_buf, sizeof(struct scus0001a_in));
                     memset(sock_buf, 0, sizeof(sock_buf));

                     struct scus0001a_out *res_buf = client.login(req_buf);
                     memcpy(sock_buf, res_buf, sizeof(struct scus0001a_out));

		     //std::cout << "sock_buf: " << sock_buf << "\n";

		     //for (int k = 0; k < 11; k++)
		     //	std::cout << "sock_buf[" << k << "]: " << (int)sock_buf[k] << "\n";

		     //std::cout << "sizeof(sock_buf): " << sizeof(sock_buf) << "\n";
		     //std::cout << "sizeof(struct scus0001a_out): " << sizeof(struct scus0001a_out) << "\n";

                     write(clnt_sock, sock_buf, sizeof(struct scus0001a_out));
                     free(res_buf);
                 }
            }
            close(clnt_sock);
            exit(0);
        }
	}

	close(serv_sock);

    return 0;
}

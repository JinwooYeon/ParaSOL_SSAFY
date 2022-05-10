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

struct payload_a {
	char msg[10];
	char len[2];
};

struct payload_b {
	char account_number[14];
};

struct scus0001a_in {
    char intnbk_user_id[10];
    char intndk_pwd[24];
};

struct scus0001a_out {
    char rst_yn[1];
    char cusno[10];
};

using grpc::Channel;
using grpc::ClientContext;
using grpc::Status;
using core_api::CoreAPI;
using core_api::AccountBalanceQueryRequest;
using core_api::AccountBalanceQueryResponse;

class CoreAPIClient {
    public:
    CoreAPIClient(std::shared_ptr<Channel> channel) : stub_(CoreAPI::NewStub(channel)) {}

    std::string getBalance(const std::string& account_number) {
        AccountBalanceQueryRequest request;
        request.set_accountnumber(account_number);

        AccountBalanceQueryResponse response;
        ClientContext context;

        Status status = stub_->getBalance(&context, request, &response);
        if (status.ok()) {
            return response.balance();
        } else {
            return "RPC Failed";
        }
    }

    struct scus0001a_out* login(const struct scus0001a_in raw_request) {
        core_api::LoginRequest request;
        request.set_id(raw_request.intnbk_user_id);
        request.set_password(raw_request.intndk_pwd);

        core_api::LoginResponse response;
        ClientContext context;

        Status status = stub_->login(&context, request, &response);

        struct scus0001a_out* raw_response = (struct scus0001a_out*)malloc(sizeof(struct scus0001a_out));
        memcpy(&raw_response->rst_yn, response.is_success().c_str(), 1);
        memcpy(&raw_response->cusno, response.cusno().c_str(), 10);

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
    std::string baseURL = "localhost:9090";

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

                if (payload_len == sizeof(struct payload_a)) {
                    struct payload_a plbuf;
                    memcpy(&plbuf, &sock_buf, sizeof(payload_a));

                    std::cout << "plbuf.msg: " << plbuf.msg << "\n";
                    std::cout << "plbuf.len: " << plbuf.len << "\n";

                    write(clnt_sock, &status, sizeof(status));
                } else if (payload_len == sizeof(struct payload_b)) {
                    struct payload_b plbuf;
                    memcpy(&plbuf, &sock_buf, sizeof(payload_b));

                    char account_number_buf[15] = "";
                    memcpy(&account_number_buf, &plbuf.account_number, 14);

                    std::cout << "account_number_buf: " << account_number_buf << "\n";

                    std::string account_number = account_number_buf;
                    std::string response = client.getBalance(account_number);
                    std::cout << "The balance: " << response << "\n";

                    write(clnt_sock, response.c_str(), response.size());
                } else if (payload_len == sizeof(struct scus0001a_in)) {
                     struct scus0001a_in req_buf;
                     memcpy(&req_buf, &sock_buf, sizeof(payload_b));

                     struct scus0001a_out *res_buf = client.login(req_buf);
                     write(clnt_sock, &res_buf, sizeof(res_buf));
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

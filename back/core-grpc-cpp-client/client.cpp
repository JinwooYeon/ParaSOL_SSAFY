#include <iostream>
#include <string>

#include <grpc++/grpc++.h>

#include "core_api.pb.h"
#include "core_api.grpc.pb.h"

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

    private:
    std::unique_ptr<CoreAPI::Stub> stub_;
};

int main(int argc, char **argv) {
    std::string buf;
    std::cout << "Input account number: ";
    std::cin >> buf;

    std::string baseURL = "localhost:9090";

    CoreAPIClient client(
        grpc::CreateChannel(baseURL, grpc::InsecureChannelCredentials())
    );

    std::string response = client.getBalance(buf);
    std::cout << "The balance: " << response << "\n";

    return 0;
}
package com.parasol.core.grpc;

import com.parasol.core.lib.AccountBalanceQueryRequest;
import com.parasol.core.lib.AccountBalanceQueryResponse;
import com.parasol.core.lib.CoreAPIGrpc;
import com.parasol.core.service.AccountService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GrpcServerService extends CoreAPIGrpc.CoreAPIImplBase {

    @Autowired
    AccountService accountService;

    @Override
    public void getBalance(AccountBalanceQueryRequest request, StreamObserver<AccountBalanceQueryResponse> responseObserver) {
        String accountNumber = request.getAccountNumber();

        Long balance = accountService.getBalance(accountNumber);

        AccountBalanceQueryResponse response = AccountBalanceQueryResponse.newBuilder()
                .setBalance(balance.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

package com.parasol.core.grpc;

import com.parasol.core.api_model.*;
import com.parasol.core_interface.*;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.BankUserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

@GrpcService
public class GrpcServerService extends CoreAPIGrpc.CoreAPIImplBase {

    @Autowired
    BankUserService bankUserService;

    @Autowired
    AccountService accountService;

    @Override
    public void login(LoginGrpcRequest request, StreamObserver<LoginGrpcResponse> responseObserver) {
        try {
            String id = request.getId();
            String password = request.getPassword();

            BankUserLoginRequest bankUserLoginRequest = BankUserLoginRequest.builder()
                    .id(id)
                    .password(password)
                    .build();

            BankUserLoginResponse bankUserLoginResponse = bankUserService.login(bankUserLoginRequest);

            LoginGrpcResponse response = LoginGrpcResponse.newBuilder()
                    .setIsSuccess("1")
                    .setCusno(bankUserLoginResponse.getCusno().toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ResponseStatusException ex) {
            LoginGrpcResponse response = LoginGrpcResponse.newBuilder()
                    .setIsSuccess("0")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onError(ex);
        }
    }

    @Override
    public void getBalance(AccountBalanceQueryGrpcRequest request, StreamObserver<AccountBalanceQueryGrpcResponse> responseObserver) {
        try {
            String accountNumber = request.getAccountNumber();

            AccountBalanceQueryRequest queryRequest = AccountBalanceQueryRequest.builder()
                    .accountNumber(accountNumber)
                    .build();

            AccountBalanceQueryResponse queryResponse = accountService.getBalance(queryRequest);

            AccountBalanceQueryGrpcResponse response = AccountBalanceQueryGrpcResponse.newBuilder()
                    .setBalance(queryResponse.getBalance().toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ResponseStatusException ex) {
            responseObserver.onError(ex);
        }
    }
}

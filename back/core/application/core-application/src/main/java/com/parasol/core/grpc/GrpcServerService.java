package com.parasol.core.grpc;

import com.parasol.core.api_model.BalanceResponse;
import com.parasol.core.api_model.BankUserLoginRequest;
import com.parasol.core.api_model.BankUserLoginResponse;
import com.parasol.core_interface.*;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.BankUserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GrpcServerService extends CoreAPIGrpc.CoreAPIImplBase {

    @Autowired
    BankUserService bankUserService;

    @Autowired
    AccountService accountService;

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        String id = request.getId();
        String password = request.getPassword();

        BankUserLoginRequest bankUserLoginRequest = BankUserLoginRequest.builder()
                .id(id)
                .password(password)
                .build();

        BankUserLoginResponse bankUserLoginResponse = bankUserService.login(bankUserLoginRequest);

        LoginResponse response = LoginResponse.newBuilder()
                .setIsSuccess(bankUserLoginResponse.isSuccess() ? "1" : "0")
                .setCusno(bankUserLoginResponse.getCusno())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getBalance(AccountBalanceQueryRequest request, StreamObserver<AccountBalanceQueryResponse> responseObserver) {
        String accountNumber = request.getAccountNumber();

        BalanceResponse balance = accountService.getBalance(accountNumber);

        AccountBalanceQueryResponse response = AccountBalanceQueryResponse.newBuilder()
                .setBalance(balance.getBalance().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

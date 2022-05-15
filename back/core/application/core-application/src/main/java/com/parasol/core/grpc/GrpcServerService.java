package com.parasol.core.grpc;

import com.parasol.core.api_model.*;
import com.parasol.core.eenum.TransactionType;
import com.parasol.core_interface.*;
import com.parasol.core.service.AccountService;
import com.parasol.core.service.BankUserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getAccountList(AccountListQueryGrpcRequest request, StreamObserver<AccountListQueryGrpcResponse> responseObserver) {
        try {
            Long cusNo = Long.parseLong(request.getCusno().trim());

            AccountListQueryRequest queryRequest = AccountListQueryRequest.builder()
                    .cusNo(cusNo)
                    .build();

            AccountListQueryResponse queryResponse = accountService.getAllAccount(queryRequest);

            String accountNumber = queryResponse.getAccounts().get(0).getAccountNumber();
            String convertedAccountNumber = accountNumber.replaceAll("-", "");

            AccountListQueryGrpcResponse response = AccountListQueryGrpcResponse.newBuilder()
                    .setDepAcno(convertedAccountNumber)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ResponseStatusException ex) {
            AccountListQueryGrpcResponse response = AccountListQueryGrpcResponse.newBuilder()
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getBalance(AccountBalanceQueryGrpcRequest request, StreamObserver<AccountBalanceQueryGrpcResponse> responseObserver) {
        try {
            Long cusNo = Long.parseLong(request.getCusno().trim());
            String accountNumber = request.getDepAcno();

            AccountBalanceQueryRequest queryRequest = AccountBalanceQueryRequest.builder()
                    .cusNo(cusNo)
                    .accountNumber(accountNumber)
                    .build();

            AccountBalanceQueryResponse queryResponse = accountService.getBalance(queryRequest);

            AccountBalanceQueryGrpcResponse response = AccountBalanceQueryGrpcResponse.newBuilder()
                    .setDepAcBlc(queryResponse.getBalance().toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ResponseStatusException ex) {
            responseObserver.onError(ex);
        }
    }

    @Override
    public void deposit(DepositQueryGrpcRequest request, StreamObserver<DepositQueryGrpcResponse> responseObserver) {
        try {
            Long amount = Long.parseLong(request.getTrxAmt().trim());
            String accountTo = request.getDepAcno().substring(0,3) + "-" + request.getDepAcno().substring(3,6) + "-" + request.getDepAcno().substring(6,12);
            String nameFrom = request.getDpstPnNm();

            DepositRequest queryRequest = DepositRequest.builder()
                    .type(TransactionType.DEPOSIT)
                    .accountTo(AccountInfo.builder().accountNumber(accountTo).build())
                    .amount(amount)
                    .nameOpponent(nameFrom)
                    .build();

            DepositResponse queryResponse = accountService.deposit(queryRequest);

            DepositQueryGrpcResponse response = DepositQueryGrpcResponse.newBuilder()
                    .setIsSuccess("1")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (ResponseStatusException ex) {
            responseObserver.onError(ex);
        }
    }
}

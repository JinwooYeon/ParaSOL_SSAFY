package com.parasol.core.grpc.client;

import com.parasol.core.lib.AccountBalanceQueryRequest;
import com.parasol.core.lib.AccountBalanceQueryResponse;
import com.parasol.core.lib.CoreAPIGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("test")
    private CoreAPIGrpc.CoreAPIBlockingStub coreStub;

    public String getBalance(final String accountNumber) throws StatusRuntimeException {
        AccountBalanceQueryRequest request = AccountBalanceQueryRequest.newBuilder()
                .setAccountNumber(accountNumber)
                .build();

        AccountBalanceQueryResponse response = this.coreStub.getBalance(request);
        return response.getBalance();
    }
}

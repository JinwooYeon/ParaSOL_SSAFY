package com.parasol.accountList.modules;

import com.parasol.accountList.api_request.AccountListQueryParam;
import com.parasol.accountList.api_request.AccountListQueryRequest;
import com.parasol.accountList.api_response.AccountListQueryResult;
import com.parasol.accountList.api_response.AccountListQueryResultResponse;
import com.parasol.accountList.api_response.LoginResult;
import com.parasol.accountList.socket_model.DepInpt;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryAccountListSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

//    public Mono<List<AccountListQueryResultResponse>> createQueryAccountListRequest(AccountListQueryParam request) {
//        try {
//            socket = new Socket(coreIp, Integer.parseInt(corePort));
//
//            DepInpt depinpt = DepInpt.builder()
//                    .cusno(request.getCusNo().toString())
//                    .build();
//
//            String dep_acno_inq_condt_d = "";
//            String dep_acno_inq_t = "";
//            Long prdt_mng_trx_d = 0L;
//            Long trx_d = 0L;
//
//            char[] payload = new char[120];
//            System.arraycopy(depinpt.getCusno().toCharArray(), 0, payload, 20, depinpt.getCusno().length());
//
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            writer = new PrintWriter(socket.getOutputStream());
//
//            writer.print(payload);
//            writer.flush();
//
//            // response 구현 해야됨
//            char[] sockBuf = new char[11];
//            int responseLen = reader.read(sockBuf);
//            if (responseLen == -1) throw new IllegalStateException("으앙");
//
//            Mono<char[]> response = Mono.just(sockBuf);
//
//            return response
////                    .flatMap(s -> {
////                        boolean success;
////                        Long cusno;
////
////                        success = (sockBuf[0] == '1');
////                        cusno = Long.parseLong(String.valueOf(sockBuf, 1, 10).trim());
////
////                        LoginResult loginResult = LoginResult.builder()
////                                .isSuccess(success)
////                                .cusNo(cusno)
////                                .build();
////
////                        return Mono.just(loginResult);
////                    });
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//
//            return null;
//        } finally {
//            try {
//                if (socket != null) { socket.close(); }
//                if (reader != null) { reader.close(); }
//                if (writer != null) { writer.close(); }
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
}

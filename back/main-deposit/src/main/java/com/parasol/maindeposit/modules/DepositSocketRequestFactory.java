package com.parasol.maindeposit.modules;

import com.parasol.maindeposit.api_model.AccountInfo;
import com.parasol.maindeposit.api_request.DepositParam;
import com.parasol.maindeposit.api_request.DepositRequest;
import com.parasol.maindeposit.api_response.DepositResponse;
import com.parasol.maindeposit.api_response.DepositResult;
import com.parasol.maindeposit.eenum.TransactionType;
import com.parasol.maindeposit.socket_model.DepInpt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class DepositSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<DepositResult> run(DepositParam request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            TransactionType transactionType = request.getMethod();
            AccountInfo accountTo = request.getAccountTo();

            if (transactionType == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            if (accountTo == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            String dep_trx_biz_d = String.valueOf(transactionType.ordinal());
            String dep_acno = accountTo.getAccountNumber().toString().replaceAll("-","");

            DepInpt depinpt = DepInpt.builder()
                    .dep_trx_biz_d(dep_trx_biz_d)
                    .dep_acno(dep_acno)
                    .build();

            String trx_amt = request.getAmount().toString();
            String dep_ac_s = "";
            String dep_trx_his_no = "";
            String lkg_yn = "";
            String lkg_ser = "";
            String dep_trx_s = "";
            String dep_trx_crt_canc_d = "";
            String dpst_pn_nm = request.getNameOpponent();

            char[] payload = new char[635];

            System.arraycopy(depinpt.getDep_trx_biz_d().toCharArray(), 0, payload, 150, depinpt.getDep_trx_biz_d().length());
            System.arraycopy(depinpt.getDep_acno().toCharArray(), 0, payload, 214, depinpt.getDep_acno().length());
            System.arraycopy(trx_amt.toCharArray(), 0, payload, 262, trx_amt.length());
            System.arraycopy(dpst_pn_nm.toCharArray(), 0, payload, 335, dpst_pn_nm.length());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.print(payload);
            writer.flush();

            char[] sockBuf = new char[129];
            int responseLen = reader.read(sockBuf);
            if (responseLen == -1) throw new IllegalStateException("으앙");

            Mono<char[]> response = Mono.just(sockBuf);

            return response
                    .flatMap(s -> {
                        String successBuf = String.valueOf(sockBuf, 128, 1).trim();
//                        String cusnoBuf = String.valueOf(sockBuf, 1, 10).trim();

                        Boolean success;
//                        Long cusno;

                        if (!StringUtils.hasText(successBuf))
                            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);

                        success = successBuf.equals("1");
//                        cusno = StringUtils.hasText(cusnoBuf) ? Long.parseLong(cusnoBuf) : null;

                        DepositResult depositResult = DepositResult.builder()
                                .isSuccess(success)
//                                .cusNo(cusno)
                                .build();

                        return Mono.just(depositResult);
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

package com.parasol.mainwithdraw.modules;

import com.parasol.mainwithdraw.api_model.AccountInfo;
import com.parasol.mainwithdraw.api_request.WithdrawParam;
import com.parasol.mainwithdraw.api_response.WithdrawResult;
import com.parasol.mainwithdraw.eenum.TransactionType;
import com.parasol.mainwithdraw.socket_model.DepInpt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class WithdrawSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<WithdrawResult> run(WithdrawParam request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            TransactionType transactionType = request.getMethod();
            AccountInfo accountFrom = request.getAccountFrom();

            if (transactionType == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            if (accountFrom == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            String dep_trx_biz_d = String.valueOf(transactionType.ordinal());
            String dep_acno = accountFrom.getAccountNumber().toString().replaceAll("-","");

            DepInpt depinpt = DepInpt.builder()
                    .dep_trx_biz_d(dep_trx_biz_d)
                    .cusno(request.getCusNo().toString())
                    .dep_acno(dep_acno)
                    .build();

            String trx_amt = request.getAmount().toString();
            String dep_ac_s = "";
            String dep_trx_his_no = "";
            String lkg_yn = "";
            String lkg_ser = "";
            String dep_trx_s = "";
            String dep_trx_crt_canc_d = "";
            String rcv_nm = request.getNameOpponent();
            String dep_ac_pwd= request.getAccountPassword();

            char[] payload = new char[659];

            System.arraycopy(depinpt.getDep_trx_biz_d().toCharArray(), 0, payload, 150, depinpt.getDep_trx_biz_d().length());
            System.arraycopy(depinpt.getCusno().toCharArray(), 0, payload, 170, depinpt.getDep_trx_biz_d().length());
            System.arraycopy(depinpt.getDep_acno().toCharArray(), 0, payload, 214, depinpt.getDep_acno().length());
            System.arraycopy(trx_amt.toCharArray(), 0, payload, 262, trx_amt.length());
            System.arraycopy(rcv_nm.toCharArray(), 0, payload, 335, rcv_nm.length());
            System.arraycopy(dep_ac_pwd.toCharArray(), 0, payload, 635, dep_ac_pwd.length());

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

                        WithdrawResult withdrawResult = WithdrawResult.builder()
                                .isSuccess(success)
//                                .cusNo(cusno)
                                .build();

                        return Mono.just(withdrawResult);
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

package com.parasol.accountList.modules;

import com.parasol.accountList.api_model.AccountNumber;
import com.parasol.accountList.api_request.AccountListQueryParam;
import com.parasol.accountList.api_request.AccountListQueryRequest;
import com.parasol.accountList.api_response.AccountListQueryResult;
import com.parasol.accountList.api_response.AccountListQueryResultResponse;
import com.parasol.accountList.api_response.LoginResult;
import com.parasol.accountList.socket_model.DepInpt;
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

    public Mono<AccountListQueryResult> createQueryAccountListRequest(AccountListQueryParam request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            DepInpt depinpt = DepInpt.builder()
                    .cusno(request.getCusNo().toString())
                    .build();

            String dep_acno_inq_condt_d = "";
            String dep_acno_inq_t = "";
            Long prdt_mng_trx_d = 0L;
            Long trx_d = 0L;

            char[] payload = new char[285];
            System.arraycopy(depinpt.getCusno().toCharArray(), 0, payload, 170, depinpt.getCusno().length());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.print(payload);
            writer.flush();

            // response 구현 해야됨
            char[] sockBuf = new char[914];
            int responseLen = reader.read(sockBuf);
            if (responseLen == -1) throw new IllegalStateException("으앙");

            Mono<char[]> response = Mono.just(sockBuf);

            return response
                    .flatMap(s -> {
                        String accountNumberBuf = String.valueOf(sockBuf, 133, 12).trim();

                        if (!StringUtils.hasText(accountNumberBuf))
                            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);

                        String accountNumber = accountNumberBuf.substring(0,3) + "-" + accountNumberBuf.substring(3,6) + "-" + accountNumberBuf.substring(6,12) ;

                        List<AccountNumber> accounts = new ArrayList<>();
                        accounts.add(new AccountNumber(accountNumber));

                        AccountListQueryResult queryResult = AccountListQueryResult.builder()
                                .accounts(accounts)
                                .build();

                        return Mono.just(queryResult);
                    });

        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        } finally {
            try {
                if (socket != null) { socket.close(); }
                if (reader != null) { reader.close(); }
                if (writer != null) { writer.close(); }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

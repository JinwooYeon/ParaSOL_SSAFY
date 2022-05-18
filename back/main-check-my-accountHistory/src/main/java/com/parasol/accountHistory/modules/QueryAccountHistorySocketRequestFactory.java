package com.parasol.accountHistory.modules;

import com.parasol.accountHistory.api_model.AccountHistory;
import com.parasol.accountHistory.api_request.AccountHistoryQueryParam;
import com.parasol.accountHistory.api_response.AccountHistoryResult;
import com.parasol.accountHistory.eenum.TransactionType;
import com.parasol.accountHistory.socket_model.DepInpt;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryAccountHistorySocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<AccountHistoryResult> createQueryAccountHistoryRequest(AccountHistoryQueryParam request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            String dep_acno = request.getAccountNumber().toString().replaceAll("-","");

            DepInpt depinpt = DepInpt.builder()
                    .cusno(request.getCusNo().toString())
                    .dep_acno(dep_acno)
                    .build();


            char[] payload = new char[335];
            System.arraycopy(depinpt.getCusno().toCharArray(), 0, payload, 170, depinpt.getCusno().length());
            System.arraycopy(depinpt.getDep_acno().toCharArray(), 0, payload, 214, depinpt.getDep_acno().length());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.print(payload);
            writer.flush();

            char[] sockBuf = new char[1276];
            int responseLen = reader.read(sockBuf);
            if (responseLen == -1) throw new IllegalStateException("으앙");

            Mono<char[]> response = Mono.just(sockBuf);

            return response
                    .flatMap(s -> {
                        String accountNumberBuf = String.valueOf(sockBuf, 133, 12).trim();
                        String methodBuf = String.valueOf(sockBuf, 914, 10).trim();
                        TransactionType method = TransactionType.DEPOSIT;

                        //여기서 걸림
                        if (!StringUtils.hasText(accountNumberBuf))
                            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);

                        String accountNumber = accountNumberBuf.substring(0,3) + "-" + accountNumberBuf.substring(3,6) + "-" + accountNumberBuf.substring(6,12) ;

                        if(methodBuf=="0") method = TransactionType.DEPOSIT;
                        else if(methodBuf=="1") method = TransactionType.WITHDRAW;


                        List<AccountHistory> histories = new ArrayList<>();
                        AccountHistory accountHistory = new AccountHistory();
                        accountHistory.setAmount(Long.parseLong(String.valueOf(sockBuf, 1254, 22).trim()));
                        accountHistory.setAccount(accountNumber);
                        accountHistory.setNameOpponent(String.valueOf(sockBuf, 954, 300).trim());
                        accountHistory.setId(Long.parseLong(String.valueOf(sockBuf, 181, 10).trim()));
                        accountHistory.setDatetime(Long.parseLong(String.valueOf(sockBuf, 173, 8).trim()));
                        accountHistory.setMethod(method);

                        histories.add(accountHistory);

                        AccountHistoryResult queryResult = AccountHistoryResult.builder()
                                .accountHistories(histories)
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

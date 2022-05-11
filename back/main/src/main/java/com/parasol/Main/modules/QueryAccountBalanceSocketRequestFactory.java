package com.parasol.Main.modules;

import com.parasol.Main.api_request.AccountBalanceQueryRequest;
import com.parasol.Main.api_response.AccountBalanceQueryResultResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

@Component
public class QueryAccountBalanceSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<AccountBalanceQueryResultResponse> createQueryAccountBalanceRequest(AccountBalanceQueryRequest request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            char[] payload = new char[14];
            for (int i = 0; i < request.getAccountNumber().length(); i++)
                payload[i] = request.getAccountNumber().charAt(i);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.print(payload);
            writer.flush();

            char[] sockBuf = new char[100];
            int responseLen = reader.read(sockBuf);
            if (responseLen == -1) throw new IllegalStateException("으앙");

            long balance = 0L;
            for (char sockChar : sockBuf)
                if (sockChar != 0) {
                    balance *= 10;
                    balance += sockChar - '0';
                } else {
                    break;
                }
            Mono<Long> response = Mono.just(balance);

            return response
                    .flatMap(s -> {
                        AccountBalanceQueryResultResponse accountBalanceQueryResultResponse = new AccountBalanceQueryResultResponse();
                        accountBalanceQueryResultResponse.setBalance(s);
                        return Mono.just(accountBalanceQueryResultResponse);
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

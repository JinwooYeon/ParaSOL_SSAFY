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

@Component
public class QueryAccountBalanceSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("{core.interface.ip}")
    private String coreIp;

    @Value("{core.interface.port}")
    private int corePort;

    public Mono<AccountBalanceQueryResultResponse> createQueryAccountBalanceRequest(AccountBalanceQueryRequest request) {
        try {
            socket = new Socket(coreIp, corePort);
            System.out.println(socket.getInetAddress().getHostAddress() + "에 연결");

            char[] payload = new char[14];
            for (int i = 0; i < request.getBankAccountNumber().length(); i++)
                payload[i] = request.getBankAccountNumber().charAt(i);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.println(payload);
            writer.flush();

            Mono<String> response = Mono.just(reader.readLine());

            return response
                    .filter(s -> !s.isEmpty())
                    .flatMap(s -> {
                        AccountBalanceQueryResultResponse accountBalanceQueryResultResponse = new AccountBalanceQueryResultResponse();
                        accountBalanceQueryResultResponse.setBalance(Long.parseLong(s));
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

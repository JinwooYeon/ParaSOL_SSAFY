package com.parasol.authentication.modules;

import com.parasol.authentication.api_request.LoginParam;
import com.parasol.authentication.api_response.LoginResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class UserLoginSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<LoginResult> userLoginRequest(LoginParam request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            char[] payload = new char[34];
            System.arraycopy(request.getId().toCharArray(), 0, payload, 0, request.getId().length());
            System.arraycopy(request.getPassword().toCharArray(), 0, payload, 10, request.getPassword().length());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.print(payload);
            writer.flush();

            char[] sockBuf = new char[11];
            int responseLen = reader.read(sockBuf);
            if (responseLen == -1) throw new IllegalStateException("으앙");

            Mono<char[]> response = Mono.just(sockBuf);

            return response
                    .flatMap(s -> {
                        boolean success;
                        Long cusno;

                        success = (sockBuf[0] == '1');
                        cusno = Long.parseLong(String.valueOf(sockBuf, 1, 10).trim());

                        LoginResult loginResult = LoginResult.builder()
                                .isSuccess(success)
                                .cusNo(cusno)
                                .build();

                        return Mono.just(loginResult);
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

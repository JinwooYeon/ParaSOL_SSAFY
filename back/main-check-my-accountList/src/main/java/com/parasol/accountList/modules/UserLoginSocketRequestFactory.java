package com.parasol.accountList.modules;

import com.parasol.accountList.api_request.LoginParam;
import com.parasol.accountList.api_response.LoginResult;
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
public class UserLoginSocketRequestFactory {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<LoginResult> userLoginRequest(LoginParam request) throws ResponseStatusException {
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
                        String successBuf = String.valueOf(sockBuf, 0, 1).trim();
                        String cusnoBuf = String.valueOf(sockBuf, 1, 10).trim();

                        boolean success;
                        Long cusno;

                        if (!StringUtils.hasText(successBuf))
                            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);

                        success = successBuf.equals("1");
                        cusno = StringUtils.hasText(cusnoBuf) ? Long.parseLong(cusnoBuf) : null;

                        LoginResult loginResult = LoginResult.builder()
                                .isSuccess(success)
                                .cusNo(cusno)
                                .build();

                        return Mono.just(loginResult);
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
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

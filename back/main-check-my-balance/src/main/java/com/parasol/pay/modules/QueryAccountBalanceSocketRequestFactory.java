package com.parasol.pay.modules;

        import com.parasol.pay.api_model.DepInpt;
        import com.parasol.pay.api_request.AccountBalanceQueryParam;
        import com.parasol.pay.api_response.AccountBalanceQueryResult;
        import com.parasol.pay.api_response.LoginResult;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.http.HttpMethod;
        import org.springframework.stereotype.Component;
        import org.springframework.web.reactive.function.BodyInserters;
        import org.springframework.web.reactive.function.client.WebClient;
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

    @Value("${core.interface.ip}")
    private String coreIp;

    @Value("${core.interface.port}")
    private String corePort;

    public Mono<AccountBalanceQueryResult> createQueryAccountBalanceRequest(AccountBalanceQueryParam request) {
        try {
            socket = new Socket(coreIp, Integer.parseInt(corePort));

            DepInpt depinpt = DepInpt.builder()
                    .cusno(request.getCusNo().toString())
                    .dep_acno(request.getAccountNumber())
                    .build();

            String dep_acno_inq_condt_d = "";
            String dep_acno_inq_t = "";
            Long prdt_mng_trx_d = 0L;
            Long trx_d = 0L;

            char[] payload = new char[120];
            System.arraycopy(depinpt.getCusno().toCharArray(), 0, payload, 20, depinpt.getCusno().length());
            System.arraycopy(depinpt.getDep_acno().toCharArray(), 0, payload, 64, depinpt.getDep_acno().length());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            writer.print(payload);
            writer.flush();

            char[] sockBuf = new char[11];
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
}

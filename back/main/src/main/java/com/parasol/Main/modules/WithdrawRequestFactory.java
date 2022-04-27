package com.parasol.Main.modules;

import com.parasol.Main.api_request.WithdrawRequest;
import com.parasol.Main.api_response.TransactionExecuteResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WithdrawRequestFactory {
    @Autowired
    @Qualifier(value = "fixedText")
    private WebClient fixedText;

    public TransactionExecuteResultResponse run(WithdrawRequest saveInfo){
        return null;
    }
}

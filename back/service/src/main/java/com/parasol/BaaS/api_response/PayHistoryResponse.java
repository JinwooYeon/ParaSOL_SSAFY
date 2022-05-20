package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.PayHistoryItem;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayHistoryResponse {
    private String total;
    private List<PayHistoryItem> data;
}

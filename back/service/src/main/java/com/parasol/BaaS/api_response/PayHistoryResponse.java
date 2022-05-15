package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.PayHistoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PayHistoryResponse {
    private Long total;
    private List<PayHistoryItem> data;
}

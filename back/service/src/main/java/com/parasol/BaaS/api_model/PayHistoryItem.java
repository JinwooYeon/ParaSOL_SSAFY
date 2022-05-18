package com.parasol.BaaS.api_model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PayHistoryItem {
    @NotNull
    private String id; // 날짜데이터
    @NotNull
    private String title; // 사용처
    @NotNull
    private String price;  // 앞에 + -
}

package com.parasol.BaaS.api_model;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PayHistoryItem {
    @NotNull
    private LocalDateTime id; // 날짜데이터
    @NotNull
    private String title; // 사용처
    @NotNull
    private Long price;
}

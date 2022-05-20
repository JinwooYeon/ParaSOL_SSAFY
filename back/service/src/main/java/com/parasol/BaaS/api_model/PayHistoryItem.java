package com.parasol.BaaS.api_model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class PayHistoryItem {
    @NotNull
    private String id; // 날짜데이터
    @NotNull
    private String title; // 사용처
    @NotNull
    private String price;  // 앞에 + -
}

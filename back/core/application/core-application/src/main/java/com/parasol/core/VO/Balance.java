package com.parasol.core.VO;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Balance {
    @PositiveOrZero
    private long balance;
}

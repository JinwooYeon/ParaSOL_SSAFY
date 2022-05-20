package com.parasol.BaaS.api_model;

import com.parasol.BaaS.enums.TransactionType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountFormattedHistory {
    @NotNull
    private Long txId;
    @NotNull
    private LocalDateTime txDateTime;
    @NotNull
    private TransactionType type;
    @PositiveOrZero
    private Long amount;
}

package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountBalance;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountBalanceQueryResultResponse extends AccountBalance {
}

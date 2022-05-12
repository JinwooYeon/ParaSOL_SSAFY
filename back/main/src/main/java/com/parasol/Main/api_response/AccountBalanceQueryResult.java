package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountBalance;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("AccountBalanceQueryResult")
public class AccountBalanceQueryResult extends AccountBalance {
}

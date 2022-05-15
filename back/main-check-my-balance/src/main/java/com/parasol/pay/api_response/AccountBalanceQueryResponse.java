package com.parasol.pay.api_response;

import com.parasol.pay.api_model.AccountBalance;
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
@ApiModel("AccountBalanceQueryResponse")
public class AccountBalanceQueryResponse extends AccountBalance {
}

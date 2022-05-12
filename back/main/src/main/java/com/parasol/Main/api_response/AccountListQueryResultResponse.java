package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountNumber;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("AccountListQueryResultResponse")
public class AccountListQueryResultResponse {
    private List<AccountNumber> accounts;
}

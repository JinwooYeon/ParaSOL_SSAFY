package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountNumber;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@ApiModel("AccountListQueryResultResponse")
public class AccountListQueryResultResponse {
    private List<AccountNumber> accounts;
}

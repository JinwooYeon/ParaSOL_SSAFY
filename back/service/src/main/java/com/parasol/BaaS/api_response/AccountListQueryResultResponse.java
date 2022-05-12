package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountListQueryResultResponse {
    private String bankName;
    private List<AccountInfo> bankAccounts;
}

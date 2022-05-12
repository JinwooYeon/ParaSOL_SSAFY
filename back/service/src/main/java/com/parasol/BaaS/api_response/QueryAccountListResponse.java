package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.AccountList;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class QueryAccountListResponse {
    private String bankName;
    private List<AccountInfo> bankAccounts;
}

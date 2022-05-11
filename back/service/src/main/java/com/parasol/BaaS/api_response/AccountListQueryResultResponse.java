package com.parasol.BaaS.api_response;

import com.parasol.BaaS.api_model.AccountList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AccountListQueryResultResponse extends AccountList {
    private String bankName;
}

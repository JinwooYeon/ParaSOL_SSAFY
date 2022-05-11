package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.AccountInfo;
import com.parasol.BaaS.api_model.LoginInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class QueryAccountHistoryRequest extends LoginInfo {
    private String bankName;
    private String bankAccountNumber;
}

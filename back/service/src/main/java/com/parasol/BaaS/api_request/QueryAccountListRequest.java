package com.parasol.BaaS.api_request;

import com.parasol.BaaS.api_model.BankInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class QueryAccountListRequest extends BankInfo {
}

package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AccountListQueryResultResponse")
public class AccountListQueryResponse {
    private List<AccountNumber> accounts;
}

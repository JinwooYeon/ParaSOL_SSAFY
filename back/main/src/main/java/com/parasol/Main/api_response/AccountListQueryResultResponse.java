package com.parasol.Main.api_response;

import com.parasol.Main.api_model.AccountList;
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
@ApiModel("AccountListQueryResultResponse")
public class AccountListQueryResultResponse extends AccountList {
}

package com.parasol.Main.api_response;

import com.parasol.Main.api_model.Transaction;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("TransactionExecuteResultResponse")
public class TransactionExecuteResultResponse extends Transaction {
}

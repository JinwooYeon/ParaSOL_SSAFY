package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("TransactionExecutionResultResponse")
public class TransactionExecutionResultResponse {
    private boolean success = false;
}

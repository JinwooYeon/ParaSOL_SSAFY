package com.parasol.Main.api_request;

import com.parasol.Main.api_model.Transaction;
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
@ApiModel("DepositParam")
public class DepositParam extends Transaction {
}

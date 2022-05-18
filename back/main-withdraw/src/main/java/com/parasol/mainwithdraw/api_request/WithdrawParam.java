package com.parasol.mainwithdraw.api_request;

import com.parasol.mainwithdraw.api_model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@ApiModel("WithdrawParam")
public class WithdrawParam extends Transaction {
    private Long cusNo;
    @ApiModelProperty(name = "account_password", example = "0809")
    @NotNull
    private String accountPassword;
}

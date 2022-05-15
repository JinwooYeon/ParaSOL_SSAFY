package com.parasol.mainwithdraw.api_response;

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
@ApiModel("WithdrawResult")
public class WithdrawResult {
    private Boolean isSuccess;
}

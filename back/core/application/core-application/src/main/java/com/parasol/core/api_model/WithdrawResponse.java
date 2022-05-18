package com.parasol.core.api_model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel("WithdrawResponse")
public class WithdrawResponse {
    @JsonProperty(value="isSuccess")
    private boolean isSuccess;
}

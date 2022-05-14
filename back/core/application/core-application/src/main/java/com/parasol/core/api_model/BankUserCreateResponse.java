package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("BankUserCreateResponse")
public class BankUserCreateResponse {
    private String id;
}

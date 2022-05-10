package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AccountListQueryRequest")
public class AccountListQueryRequest {
    @NotBlank
    private String id;
}

package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ClientCreateResponse")
public class ClientCreateResponse {
    private long id;
}

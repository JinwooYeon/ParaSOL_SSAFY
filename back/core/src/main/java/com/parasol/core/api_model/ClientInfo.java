package com.parasol.core.api_model;

import com.parasol.core.entity.Client;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("ClientInfo")
public class ClientInfo extends Client {
}

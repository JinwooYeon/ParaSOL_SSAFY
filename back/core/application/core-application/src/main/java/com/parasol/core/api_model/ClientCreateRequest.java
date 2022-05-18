package com.parasol.core.api_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ClientCreateRequest")
public class ClientCreateRequest extends ClientInfo{
    @ApiModelProperty(name="residentNumber", example = "991228-1234567")
    @NotBlank
    @Size(max = 14, min = 14)
    @Pattern(regexp="^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$")
    private String residentNumber;
}

package com.parasol.core.api_model;

import com.parasol.core.entity.BankUser;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ClientInfo")
public class ClientInfo {
    @Nullable
    private long id;

    @NotBlank
    private String name;

    @Nullable
    private BankUser bankUser;
}

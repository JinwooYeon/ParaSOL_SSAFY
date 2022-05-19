package com.parasol.BaaS.api_model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class LoginInfo {
    private String id;
    private String password;
}

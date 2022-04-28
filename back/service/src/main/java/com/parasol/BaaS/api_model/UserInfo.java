package com.parasol.BaaS.api_model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserInfo extends LoginInfo{
    private String name;
}

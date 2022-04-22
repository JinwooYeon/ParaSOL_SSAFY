package com.parasol.Main.api_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account {
    private String id;
    private Integer password;
    private Long balance;
}

package com.parasol.BaaS.api_model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountList {
    private List<AccountInfo> accounts;
}

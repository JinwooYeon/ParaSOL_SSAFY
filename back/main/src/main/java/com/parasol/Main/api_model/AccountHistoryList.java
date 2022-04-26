package com.parasol.Main.api_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AccountHistoryList {
    private List<AccountHistory> accountHistories;
}

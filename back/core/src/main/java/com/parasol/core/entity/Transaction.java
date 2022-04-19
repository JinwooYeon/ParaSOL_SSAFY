package com.parasol.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Transaction {
    private Long transactionSeq;
    private Long transactionDate;
    private String transactionType;
    private Long transactionAmount;
    private String transactionAccount;
}

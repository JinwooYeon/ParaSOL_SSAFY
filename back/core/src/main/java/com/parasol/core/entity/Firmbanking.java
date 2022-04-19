package com.parasol.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Firmbanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long firmbankingSeq;

    private String firmbankingName;
    private String firmbankingType;
    private String firmbankingDepositAccountNo;
    private String firmbankingWithdrawAccountNo;
}

package com.parasol.core.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.parasol.core.eenum.TransactionType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionSeq;

    private Long transactionDate;
    private TransactionType transactionType;
    private Long transactionAmount;
    private String transactionAccount;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "account_no")
    private Account account;
}

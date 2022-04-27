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
    private Long id;

    private Long date;
    private TransactionType type;
    private Long amount;
    private String transactionAccount;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;
}
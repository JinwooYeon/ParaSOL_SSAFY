package com.parasol.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Account {
    @Id
    private String id;

    private Integer password;

    private Long balance = Long.valueOf(0);

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id2")
    private Client client;

    @JsonManagedReference
    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> transactionHistories;
}

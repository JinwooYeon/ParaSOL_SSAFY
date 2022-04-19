package com.parasol.core.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
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
    private String accountNo;

    private Integer accountPassword;


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "clientSeq")
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
}

package com.parasol.core.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Account {
    @Id
    private String id;
    
    @NotBlank
    @Size(min = 4, max = 4)
    private String password;

    private Long balance = Long.valueOf(0);

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "id2")
    private Client client;

    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    @OneToMany(mappedBy = "account")
    private List<TransactionHistory> transactionHistories;
}

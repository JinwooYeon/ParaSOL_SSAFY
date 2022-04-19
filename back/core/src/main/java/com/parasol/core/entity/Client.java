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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientSeq;

    private String clientName;
    private String clientResidentNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "account_no")
    List<Account> accounts;
};

package com.parasol.BaaS.db.entity;

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
public class BankConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankSeq;

    private String bankName;
    private String bankId;
    private String bankPassword;

    @ManyToOne
    @JoinColumn(name="user_seq")
    private User user;
}

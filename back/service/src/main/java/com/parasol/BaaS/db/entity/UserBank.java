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
public class UserBank {
    @EmbeddedId
    private UserBankId userBankId;

    @ManyToOne
    @MapsId("userSeq")
    @JoinColumn(name = "user_seq")
    private User user;


    @OneToOne
    @MapsId("bankSeq")
    @JoinColumn(name = "bank_seq")
    private BankConnection bank;
}

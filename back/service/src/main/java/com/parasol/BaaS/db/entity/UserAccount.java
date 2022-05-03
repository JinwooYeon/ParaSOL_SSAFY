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
public class UserAccount {
    @EmbeddedId
    private UserAccountId userAccountId;

    @ManyToOne
    @MapsId("userSeq")
    @JoinColumn(name = "user_seq")
    private User user;

    @OneToOne
    @MapsId("accountSeq")
    @JoinColumn(name = "account_seq")
    private Account account;

}

package com.parasol.BaaS.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class PayLedger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ledgerId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User owner;

    private LocalDateTime lastTxDatetime;
    private Long balance;

    @OneToOne
    @JoinColumn(name = "account_seq")
    private Account account;
}

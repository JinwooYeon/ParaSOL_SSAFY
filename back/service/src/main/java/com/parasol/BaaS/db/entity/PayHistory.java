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
public class PayHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private LocalDateTime txDatetime;
    private String txOpponent;
    private Long amount;

}

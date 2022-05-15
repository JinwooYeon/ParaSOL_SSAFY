package com.parasol.BaaS.db.entity;

import com.parasol.BaaS.enums.TransactionType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private TransactionType type;
}

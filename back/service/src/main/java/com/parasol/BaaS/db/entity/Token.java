package com.parasol.BaaS.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Id
    private Long tokenId;

    private String refreshToken;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_seq")
    private User user;
}

package com.parasol.core.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankUser {
    @Id
    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="client_id")
    private Client client;
};

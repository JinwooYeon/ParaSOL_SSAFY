package com.parasol.core.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.UniqueElements;

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
public class Client {
    @Id
    private String id;

    @NotBlank
    private String name;
    @NotBlank
    @Column(unique=true)
    private String residentNumber;

    @OneToOne
    @JoinColumn(name="bank_user_id")
    private BankUser bankUser;
};

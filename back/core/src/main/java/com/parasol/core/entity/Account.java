package com.parasol.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@OneToMany
public class Account {
    private String accountNo;
    private Integer accountPassword;
}

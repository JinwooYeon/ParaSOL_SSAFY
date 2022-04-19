package com.parasol.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
@OneToMany
public class User {
    private Long clientSeq;
    private String clientName;
    private String clientResidentNumber;
}

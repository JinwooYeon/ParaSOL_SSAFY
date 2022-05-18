package com.parasol.BaaS.db.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
public class UserAccountId implements Serializable {
    private Long userSeq;
    private Long accountSeq;
}

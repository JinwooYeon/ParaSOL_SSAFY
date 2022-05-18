package com.parasol.BaaS.db.entity;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
public class UserBankId implements Serializable {
    private Long userSeq;
    private Long bankSeq;
}

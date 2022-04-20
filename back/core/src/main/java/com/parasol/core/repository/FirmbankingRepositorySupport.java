package com.parasol.core.repository;

import com.parasol.core.entity.Firmbanking;
import com.parasol.core.entity.QFirmbanking;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FirmbankingRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    private QFirmbanking qFirmbanking = QFirmbanking.firmbanking;

    public Optional<List<Firmbanking>> getAllFirbanking(){
        List<Firmbanking> result = jpaQueryFactory.select(qFirmbanking).from(qFirmbanking).fetch();
        
        return Optional.of(result);
    }
}

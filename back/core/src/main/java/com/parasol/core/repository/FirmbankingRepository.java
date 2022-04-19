package com.parasol.core.repository;

import com.parasol.core.entity.Firmbanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmbankingRepository extends JpaRepository<Firmbanking, Long> {
}

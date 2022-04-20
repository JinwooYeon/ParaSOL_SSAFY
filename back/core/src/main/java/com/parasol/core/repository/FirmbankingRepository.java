package com.parasol.core.repository;

import com.parasol.core.entity.Firmbanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirmbankingRepository extends JpaRepository<Firmbanking, Long> {
    Optional<Firmbanking> findFirmbankingsByFirmbankingName(String name);
    Optional<Firmbanking> findFirmbankingsByFirmbankingNameAndFirmbankingType(String name, String type);
    Optional<Firmbanking> findFirmbankingsByFirmbankingWithdrawAccountNo(String withdrawAccountNo);
    Optional<Firmbanking> findFirmbankingsByFirmbankingDepositAccountNo(String depositAccountNo);
}

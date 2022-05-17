package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.PayHistory;
import com.parasol.BaaS.db.entity.PayLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayLedgerRepository extends JpaRepository<PayLedger, Long> {
    Optional<PayLedger> findByOwnerUserId(String id);
    Long deleteByOwnerUserId(String id);
    
}

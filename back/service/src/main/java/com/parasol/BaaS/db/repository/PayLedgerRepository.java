package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.PayHistory;
import com.parasol.BaaS.db.entity.PayLedger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayLedgerRepository extends JpaRepository<PayLedger, Long> {
    
}

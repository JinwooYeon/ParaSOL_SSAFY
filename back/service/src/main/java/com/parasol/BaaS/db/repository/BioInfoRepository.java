package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.BioInfo;
import com.parasol.BaaS.db.entity.PayLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BioInfoRepository extends JpaRepository<BioInfo, Long> {
    Optional<BioInfo> findByOwnerUserId(String id);
    Long deleteByOwnerUserId(String id);
}

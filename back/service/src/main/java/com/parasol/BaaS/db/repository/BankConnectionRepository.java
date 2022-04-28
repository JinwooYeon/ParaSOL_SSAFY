package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.BankConnection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankConnectionRepository extends JpaRepository<BankConnection, Long> {
}

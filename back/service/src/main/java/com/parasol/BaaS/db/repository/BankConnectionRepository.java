package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.BankConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankConnectionRepository extends JpaRepository<BankConnection, Long> {
    Optional<BankConnection> findByUser_UserSeqAndBankName(Long userSeq, String bankName);
}

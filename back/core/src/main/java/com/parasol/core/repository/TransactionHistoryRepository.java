package com.parasol.core.repository;

import com.parasol.core.entity.Account;
import com.parasol.core.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<Transaction, Long> {
}

package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.Account;
import com.parasol.BaaS.db.entity.PayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayHistoryRepository  extends JpaRepository<PayHistory, Long> {
    List<PayHistory> findByUser_UserId(String id);
}

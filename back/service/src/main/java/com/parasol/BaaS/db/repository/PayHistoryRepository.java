package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.Account;
import com.parasol.BaaS.db.entity.PayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayHistoryRepository  extends JpaRepository<PayHistory, Long> {

}

package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

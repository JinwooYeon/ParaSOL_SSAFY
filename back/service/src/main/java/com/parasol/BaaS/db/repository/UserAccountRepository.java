package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.UserAccount;
import com.parasol.BaaS.db.entity.UserAccountId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, UserAccountId> {
}

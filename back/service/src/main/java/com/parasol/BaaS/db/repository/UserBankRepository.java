package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.UserBank;
import com.parasol.BaaS.db.entity.UserBankId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBankRepository extends JpaRepository<UserBank, UserBankId> {
}

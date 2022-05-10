package com.parasol.core.repository;

import com.parasol.core.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface BankUserRepository extends JpaRepository<BankUser, String> {
    Optional<BankUser> findByUsername(String username);
}

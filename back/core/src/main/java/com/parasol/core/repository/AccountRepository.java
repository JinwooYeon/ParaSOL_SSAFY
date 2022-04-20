package com.parasol.core.repository;

import com.parasol.core.entity.Account;
import com.parasol.core.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNo(String accountNo);
    Optional<Account> findById(Long id);
    List<Account> findAll();
}

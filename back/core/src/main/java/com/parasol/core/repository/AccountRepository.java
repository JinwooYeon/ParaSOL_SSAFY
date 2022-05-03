package com.parasol.core.repository;

import com.parasol.core.entity.Account;
import com.parasol.core.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByClient(Client client);
    List<Account> findAll();
}

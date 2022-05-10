package com.parasol.core.repository;

import com.parasol.core.entity.BankUser;
import com.parasol.core.entity.Client;
import com.parasol.core.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByNameAndResidentNumber(String name, String residentNumber);
    Optional<Client> findByBankUser_Id(String id);
}
package com.parasol.core.repository;

import com.parasol.core.entity.Client;
import com.parasol.core.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByNameAndResidentNumber(String name, String residentNumber);
}
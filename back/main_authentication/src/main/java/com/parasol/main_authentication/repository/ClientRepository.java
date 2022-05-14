package com.parasol.main_authentication.repository;


import com.parasol.main_authentication.entity.ApiKey;
import com.parasol.main_authentication.entity.Client;
import com.parasol.main_authentication.enums.MethodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByMethodAndClientIdAndPermitEndpoint(MethodType method, ApiKey clientId, String permitEndpoint);
}

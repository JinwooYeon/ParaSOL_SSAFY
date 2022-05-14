package com.parasol.main_authentication.repository;

import com.parasol.main_authentication.entity.Account;
import com.parasol.main_authentication.entity.ApiKey;
import com.parasol.main_authentication.enums.MethodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByMethodAndClientIdAndPermitEndpoint(MethodType method, ApiKey clientId, String permitEndpoint);
}
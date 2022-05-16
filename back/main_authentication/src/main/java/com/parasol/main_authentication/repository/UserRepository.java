package com.parasol.main_authentication.repository;

import com.parasol.main_authentication.entity.ApiKey;
import com.parasol.main_authentication.entity.User;
import com.parasol.main_authentication.enums.MethodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMethodAndClientIdAndPermitEndpoint(MethodType method, ApiKey clientId, String permitEndpoint);
}

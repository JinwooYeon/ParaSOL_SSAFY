package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);

    Long deleteByUserId(String userId);
}

package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository <Token, Long> {
    Optional<Token> findByUser_UserId(String id);

    Long deleteByUserUserId(String id);
}

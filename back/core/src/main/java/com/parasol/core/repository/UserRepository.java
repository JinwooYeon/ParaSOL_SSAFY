package com.parasol.core.repository;

import com.parasol.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByClientSeq(Long clientSeq);
}

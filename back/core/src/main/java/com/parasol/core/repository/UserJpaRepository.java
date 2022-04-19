package com.parasol.core.repository;

import com.parasol.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User,Long>, UserRepository {
    @Override
    User save(User user);

    @Override
    Optional<User> findByClientSeq(Long clientSeq);
}

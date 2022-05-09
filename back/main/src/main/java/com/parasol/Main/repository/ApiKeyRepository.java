package com.parasol.Main.repository;

import com.parasol.Main.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    long count();
}

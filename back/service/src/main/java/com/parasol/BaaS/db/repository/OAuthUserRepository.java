package com.parasol.BaaS.db.repository;

import com.parasol.BaaS.db.entity.OAuthUser;
import com.parasol.BaaS.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthUserRepository extends JpaRepository<OAuthUser, String> {

}

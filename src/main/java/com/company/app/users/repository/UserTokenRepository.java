package com.company.app.users.repository;

import java.util.Optional;

import com.company.app.users.model.UserToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long>{
    public Optional<UserToken> findByUserId(Long userId);
}

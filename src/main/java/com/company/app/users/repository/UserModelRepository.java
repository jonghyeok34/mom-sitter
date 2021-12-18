package com.company.app.users.repository;

import java.util.Optional;

import com.company.app.users.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByEmail(String email);
}

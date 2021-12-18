package com.company.app.users.repository;

import com.company.app.users.model.UserToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken, String>{
    public UserToken findByUserId(String userId);
}

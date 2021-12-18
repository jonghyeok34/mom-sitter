package com.company.app.users.repository;

import com.company.app.users.model.ChildInfoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildInfoModelRepository extends JpaRepository<ChildInfoModel, Long>{
    
}

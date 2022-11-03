package com.peazy.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peazy.auth.model.entity.SupplierUserEntity;

@Repository
public interface SupplierUserRepository extends JpaRepository<SupplierUserEntity, Long> {
    
    Optional<SupplierUserEntity> findByUserEmail(String email);

    Optional<SupplierUserEntity> findByUserName(String userName);

}
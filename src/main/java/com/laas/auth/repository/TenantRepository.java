package com.laas.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laas.auth.model.entity.TenantEntity;

public interface TenantRepository extends JpaRepository<TenantEntity, Long> {

Optional<TenantEntity>	findByTenantCodeAndAuthKey(String tenantCode,String authKey);

}
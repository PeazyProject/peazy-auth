package com.laas.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laas.auth.model.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
	List<PermissionEntity> findByIsActivated(Integer isActivated);
}

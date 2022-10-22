package com.laas.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laas.auth.model.entity.UserScopeEntity;

public interface UserScopeRepository extends JpaRepository<UserScopeEntity, Long> {
	void deleteByUserUUID(String userUUID);
	List<UserScopeEntity> findByUserUUID(String userUUID);
	List<UserScopeEntity> findByUserUUIDAndRoleSeqNo(String userId, Long roleSeqNo);
}

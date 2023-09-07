package com.peazy.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peazy.auth.model.dto.UserRoleDto;
import com.peazy.auth.model.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
	void deleteByUserUUID(String userId);
	
	@Query(value = " SELECT " +
			" ur.UserUUID, " +
			" ur.RoleSeqNo, " +
			" r.RoleCode, " +
			" r.RoleName " +
			" FROM alanlee.Auth_UserRole AS ur " +
			" INNER JOIN alanlee.Auth_Role AS r ON ur.RoleSeqNo = r.SeqNo " +
			" WHERE 1 = 1 " +
			" AND UserUUID = :userUUID " +
			" AND r.IsActivated = 1 ", nativeQuery = true)
	List<UserRoleDto> findRolesByUser(@Param("userUUID") String loginAccount);
}

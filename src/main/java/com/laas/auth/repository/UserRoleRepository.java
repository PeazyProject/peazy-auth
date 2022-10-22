package com.laas.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laas.auth.model.dto.UserRoleDto;
import com.laas.auth.model.entity.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
	void deleteByUserUUID(String userId);
	
	@Query(value = "select  " + 
			"[userRole].UserUUID,  " + 
			"[userRole].RoleSeqNo,  " + 
			"[Role].RoleCode,  " + 
			"[Role].RoleName " + 
			"from [Auth].[UserRole] [userRole] (nolock) " + 
			"inner join [Auth].[Role] [Role] (nolock) on [userRole].RoleSeqNo = [Role].SeqNo " + 
			"where UserUUID = :userUUID " + 
			"and [Role].IsActivated = 1", nativeQuery = true)
	List<UserRoleDto> findRolesByUser(@Param("userUUID") String loginAccount);
}

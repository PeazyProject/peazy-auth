package com.laas.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laas.auth.model.dto.UserPermissionDto;
import com.laas.auth.model.entity.RolePermissionEntity;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
	
	@Query(value = "select  " + 
			"[rolepermission].PermissionSeqNo,  " + 
			"[permission].PermissionName, " + 
			"[permission].PermissionCode " + 
			"from [Auth].[RolePermission] [rolepermission] (nolock) " + 
			"inner join [Auth].Permission [permission] (nolock) on [rolepermission].PermissionSeqNo = [permission].SeqNo and [permission].IsActivated = 1 " + 
			"inner join [Auth].[Role] [role] (nolock) on [rolepermission].RoleSeqNo = [role].SeqNo and [role].IsActivated = 1 " + 
			"where [rolepermission].RoleSeqNo = :roleSeqNo", nativeQuery = true)
	List<UserPermissionDto> findPermissionByRole(@Param("roleSeqNo") Long roleSeqNo);
	
	void deleteByRoleSeqNo(Long roleSeqNo);
}

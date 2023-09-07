package com.peazy.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peazy.auth.model.dto.UserPermissionDto;
import com.peazy.auth.model.entity.RolePermissionEntity;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {
	
	@Query(value = " select " +    
	" arp.PermissionSeqNo, " +
	" ap.PermissionName, " +
	" ap.PermissionCode " +
	" from alanlee.Auth_RolePermission arp " +
	" inner join alanlee.Auth_Permission ap on arp.PermissionSeqNo = ap.SeqNo and ap.IsActivated = 1 " +   
	" inner join alanlee.Auth_Role ar on arp.RoleSeqNo = ar.SeqNo and ar.IsActivated = 1 " +   
	" where arp.RoleSeqNo = :roleSeqNo "
	, nativeQuery = true)
	List<UserPermissionDto> findPermissionByRole(@Param("roleSeqNo") Long roleSeqNo);
	
	void deleteByRoleSeqNo(Long roleSeqNo);
}

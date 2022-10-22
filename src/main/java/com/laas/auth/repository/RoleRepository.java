package com.laas.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laas.auth.model.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	List<RoleEntity> findByUserTypeAndIsActivated(String userType, Integer isActivated);
	
	@Query(value = "select  " + 
			"* " + 
			"from [Auth].[Role](nolock) " + 
			"where 1=1 " + 
			"and  " + 
			"( " + 
			"	( " + 
			"		:userType is null or :userType = '' " + 
			"	) " + 
			"	OR " + 
			"	( " + 
			"		:userType is not null " + 
			"		AND ( " + 
			"			UserType = :userType " + 
			"		)  " + 
			"	) " + 
			") " + 
			"and  " + 
			"( " + 
			"	( " + 
			"		:roleCode is null or :roleCode = '' " + 
			"	) " + 
			"	OR " + 
			"	( " + 
			"		:roleCode is not null " + 
			"		AND ( " + 
			"			RoleCode like CONCAT('%', :roleCode, '%') " + 
			"		)  " + 
			"	) " + 
			") " + 
			"and  " + 
			"( " + 
			"	( " + 
			"		:roleName is null or :roleName = '' " + 
			"	) " + 
			"	OR " + 
			"	( " + 
			"		:roleName is not null " + 
			"		AND ( " + 
			"			RoleName like CONCAT('%', :roleName, '%') " + 
			"		)  " + 
			"	) " + 
			")", nativeQuery = true)
	List<RoleEntity> findByFilter(@Param("userType") String userType, @Param("roleCode") String roleCode, @Param("roleName") String roleName);
}

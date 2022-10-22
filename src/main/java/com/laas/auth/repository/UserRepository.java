package com.laas.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.laas.auth.model.dto.QueryUserDto;
import com.laas.auth.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	
	Optional<UserEntity> findByUuid(String uuid);
	
	Optional<UserEntity> findByLoginAccount(String loginAccount);
	
	Optional<UserEntity> findByEmail(String email);
	
	@Query(value = ";WITH UserAuth AS       " + 
			"(      " + 
			"	SELECT *      " + 
			"	FROM OpenJson(:json)      " + 
			"	WITH (     " + 
			"		TENANT NVARCHAR(50) '$.tenant',     " + 
			"		DEPT NVARCHAR(50) '$.dept',    " + 
			"		CUSTOMER NVARCHAR(50) '$.customer',     " + 
			"		FACILITY NVARCHAR(50) '$.facility',    " + 
			"		ORGID NVARCHAR(50) '$.orgId',    " + 
			"		BRAND NVARCHAR(50) '$.brand',   " + 
			"		FORWARDER NVARCHAR(50) '$.forwarder'   " + 
			"	)    " + 
			"	union   " + 
			"	select '','','','','','',''  " + 
			")   " + 
			"select      " + 
			"*   " + 
			"from [Auth].[User](nolock) u    " + 
			"where 1=1     " + 
			"and      " + 
			"(     " + 
			"	:loginAccount IS NULL OR :loginAccount = ''     " + 
			"	OR     " + 
			"	(     " + 
			"		:loginAccount IS NOT NULL     " + 
			"		AND     " + 
			"		(     " + 
			"			LoginAccount like CONCAT('%', :loginAccount, '%')     " + 
			"		)     " + 
			"	)     " + 
			")     " + 
			"and     " + 
			"(     " + 
			"	:userName IS NULL OR :userName = ''     " + 
			"	OR     " + 
			"	(     " + 
			"		:userName IS NOT NULL     " + 
			"		AND     " + 
			"		(     " + 
			"			UserName like CONCAT('%', :userName, '%')     " + 
			"		)     " + 
			"	)     " + 
			")     " + 
			"and      " + 
			"(     " + 
			"	:email IS NULL OR :email = ''     " + 
			"	OR     " + 
			"	(     " + 
			"		:email IS NOT NULL     " + 
			"		AND     " + 
			"		(     " + 
			"			Email like CONCAT('%', :email, '%')     " + 
			"		)     " + 
			"	)     " + 
			")   " + 
			"and  " + 
			"( " + 
			"	:userType = 'SuperAdmin' " + 
			"	OR " + 
			"	( " + 
			"		:userType <> 'SuperAdmin' " + 
			"		AND " + 
			"		( " + 
			"			UUID IN    " + 
			"			(   " + 
			"				select UserUUID from [Auth].UserScope A   " + 
			"				where EXISTS   " + 
			"				(   " + 
			"						SELECT * FROM UserAuth   " + 
			"						WHERE (A.TenantCode = UserAuth.TENANT OR UserAuth.TENANT IS NULL)   " + 
			"						AND (A.Dept = UserAuth.DEPT OR UserAuth.DEPT IS NULL)   " + 
			"						AND (A.Customer = UserAuth.CUSTOMER OR UserAuth.CUSTOMER IS NULL)   " + 
			"						AND (A.FacilityCode = UserAuth.FACILITY OR UserAuth.Facility IS NULL)   " + 
			"						AND (A.OrgId = UserAuth.ORGID OR UserAuth.ORGID IS NULL)   " + 
			"						AND (A.ForwarderCode = UserAuth.FORWARDER OR UserAuth.FORWARDER IS NULL)   " + 
			"						AND (A.Brand = UserAuth.BRAND OR UserAuth.BRAND IS NULL)   " + 
			"				)   " + 
			"			)   " + 
			"		) " + 
			"	) " + 
			")  " + 
			"and " + 
			"( " +
			":roleCode IS NULL OR :roleCode = '' " + 
			"    OR " + 
			"    ( " + 
			"        :roleCode IS NOT NULL " + 
			"        AND EXISTS" + 
			"        ( " + 
			"            SELECT * FROM Auth.UserRole ur " + 
			"            LEFT JOIN [Auth].[Role] r " + 
			"            ON ur.RoleSeqNo = r.SeqNo " + 
			"            WHERE u.UUID = ur.UserUUID " +
			"            AND (:roleCode IS NULL OR r.RoleCode = :roleCode) " + 
			"        ) " + 
			"    ) " + 
			") ", nativeQuery = true)
	List<QueryUserDto> getUsersByFilter(
			@Param("loginAccount") String loginAccount, 
			@Param("userName") String userName, 
			@Param("email") String email, 
			@Param("json") String dataScopes,
			@Param("userType") String userType,
			@Param("roleCode") String roleCode);

}
package com.peazy.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.peazy.auth.model.entity.UserScopeEntity;

public interface UserScopeRepository extends JpaRepository<UserScopeEntity, Long> {
	void deleteByUserUuid(String userUuid);
	List<UserScopeEntity> findByUserUuid(String userUuid);
	List<UserScopeEntity> findByUserUuidAndRoleSeqNo(String userUuid, Long roleSeqNo);
	
	// @Query(value = "SELECT count(*) From [Auth].[UserScope] "
	//         + "INNER JOIN [Auth].[User] on [UserScope].[UserUUID] = [User].[UUID] "
	//         + "WHERE 1 = 1 "
	//         + "AND [User].[LoginAccount] = :subscriberId "
	//         + "AND EXISTS " 
	//         + "  ( " 
	//         + "  SELECT * FROM [Auth].[UserScope] " 
    //         + "  WHERE [UserScope].UserUUID = [User].[UUID] "
    //         + "  AND (:tenant = [UserScope].TenantCode OR [UserScope].TenantCode IS NULL OR :tenant IS NULL) " 
	//         + "  AND (:orgId = [UserScope].OrgId OR [UserScope].OrgId IS NULL OR :orgId IS NULL) " 
	//         + "  AND (COALESCE(:brand, NULL) IS NULL OR [UserScope].Brand in (:brand) OR [UserScope].Brand IS NULL) "
    //         + "  AND (COALESCE(:dept, NULL) IS NULL OR [UserScope].Dept in (:dept) OR [UserScope].Dept IS NULL) " 
    //         + "  AND (COALESCE(:customer, NULL) IS NULL OR [UserScope].Customer in (:customer) OR [UserScope].Customer IS NULL) "
    //         + "  AND (:facilityCode = [UserScope].FacilityCode OR [UserScope].FacilityCode IS NULL OR :facilityCode IS NULL) "
    //         + "  AND (:forwarderCode = [UserScope].ForwarderCode OR [UserScope].ForwarderCode IS NULL OR :forwarderCode IS NULL) "
	//         + "  )" ,nativeQuery = true)
	// int countLoginAccountByUserScope(
	// @Param("tenant") String tenant,
	// @Param("orgId") String orgId,
	// @Param("dept") List<String> dept,
	// @Param("brand") List<String> brand,
	// @Param("customer") List<String> customer,
	// @Param("facilityCode") String facilityCode,
	// @Param("forwarderCode") String forwarderCode,
	// @Param("subscriberId") String subscriberId);

}

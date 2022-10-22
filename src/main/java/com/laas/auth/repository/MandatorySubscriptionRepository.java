package com.laas.auth.repository;

import java.util.List;

import com.laas.auth.model.entity.MandatorySubscriptionEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MandatorySubscriptionRepository extends JpaRepository<MandatorySubscriptionEntity, Long> {

	List<MandatorySubscriptionEntity> findByUserTypeAndIsActivatedAndRoleCode(String userType,Integer isActivated, String roleCode);
    
}

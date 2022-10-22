package com.laas.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laas.auth.model.entity.ADRedirectSettingEntity;

public interface ADRedirectSettingRepository extends JpaRepository<ADRedirectSettingEntity, Long> {
	Optional<ADRedirectSettingEntity> findByEmailSuffix(String emailSuffix);
}

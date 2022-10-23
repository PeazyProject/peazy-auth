package com.peazy.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peazy.auth.model.entity.CustomerUserEntity;

@Repository
public interface CustomerUserRepository extends JpaRepository<CustomerUserEntity, Long> {

	
	Optional<CustomerUserEntity> findByuserUUid(String uuid);
	
	Optional<CustomerUserEntity> findByUserAccount(String loginAccount);
	
	Optional<CustomerUserEntity> findByUserEmail(String email);

}
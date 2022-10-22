package com.peazy.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peazy.auth.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	
	Optional<UserEntity> findByuserUUid(String uuid);
	
	Optional<UserEntity> findByUserAccount(String loginAccount);
	
	Optional<UserEntity> findByUserEmail(String email);

}
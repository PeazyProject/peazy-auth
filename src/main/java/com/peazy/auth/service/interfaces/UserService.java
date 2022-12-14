package com.peazy.auth.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.entity.UserEntity;

@Service
public interface UserService {
	
	List<UserEntity> getUser() throws JsonProcessingException;

	Optional<UserEntity> findByUserName(String username);

	Optional<UserEntity> findByUserEmail(String email);
}

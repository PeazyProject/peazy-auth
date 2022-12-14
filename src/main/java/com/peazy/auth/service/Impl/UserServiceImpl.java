package com.peazy.auth.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.entity.UserEntity;
import com.peazy.auth.repository.UserRepository;
import com.peazy.auth.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserEntity> getUser() throws JsonProcessingException {
		List<UserEntity> userEntities = userRepository.findAll();
		return userEntities;
	}

	@Override
	public Optional<UserEntity> findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	public Optional<UserEntity> findByUserEmail(String email) {
		return userRepository.findByUserEmail(email);
	}

}

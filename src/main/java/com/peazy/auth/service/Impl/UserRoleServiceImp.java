package com.peazy.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peazy.auth.model.dto.UserRoleDto;
import com.peazy.auth.repository.UserRoleRepository;
import com.peazy.auth.service.interfaces.UserRoleService;

@Service
public class UserRoleServiceImp implements UserRoleService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public List<UserRoleDto> getUserRoles(String userId) {
		List<UserRoleDto> roles = userRoleRepository.findRolesByUser(userId);
		return roles;
	}
	
}

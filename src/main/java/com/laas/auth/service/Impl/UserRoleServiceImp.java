package com.laas.auth.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laas.auth.model.dto.UserRoleDto;
import com.laas.auth.repository.UserRoleRepository;
import com.laas.auth.service.interfaces.UserRoleService;

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

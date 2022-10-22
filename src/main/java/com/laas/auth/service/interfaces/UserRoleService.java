package com.laas.auth.service.interfaces;

import java.util.List;

import com.laas.auth.model.dto.UserRoleDto;

public interface UserRoleService {
	List<UserRoleDto> getUserRoles(String userId);
}

package com.peazy.auth.service.interfaces;

import java.util.List;

import com.peazy.auth.model.dto.UserRoleDto;

public interface UserRoleService {
	List<UserRoleDto> getUserRoles(String userId);
}

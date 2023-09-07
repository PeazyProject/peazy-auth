package com.peazy.auth.service.interfaces;

import java.util.List;

import com.peazy.auth.model.dto.UserPermissionDto;
import com.peazy.auth.model.entity.PermissionEntity;

public interface PermissionService {
	List<UserPermissionDto> getPermissionByRole(Long roleSeqNo);
	List<PermissionEntity> getAllPermission();
	// void modifyRolePermission(Long roleSeqNo, String currentUser, List<RolePermissionRequest> model);
}

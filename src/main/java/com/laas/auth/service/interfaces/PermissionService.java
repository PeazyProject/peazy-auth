package com.laas.auth.service.interfaces;

import java.util.List;

import com.laas.auth.model.args.RolePermissionRequest;
import com.laas.auth.model.dto.UserPermissionDto;
import com.laas.auth.model.entity.PermissionEntity;

public interface PermissionService {
	List<UserPermissionDto> getPermissionByRole(Long roleSeqNo);
	List<PermissionEntity> getAllPermission();
	void modifyRolePermission(Long roleSeqNo, String currentUser, List<RolePermissionRequest> model);
}

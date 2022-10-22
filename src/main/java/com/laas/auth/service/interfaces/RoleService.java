package com.laas.auth.service.interfaces;

import java.util.List;

import com.laas.auth.model.args.QueryRoleRequest;
import com.laas.auth.model.dto.UserPermissionDto;
import com.laas.auth.model.entity.RoleEntity;
import com.laas.auth.model.resp.RoleOptions;
import com.laas.auth.model.resp.RoleResp;

public interface RoleService {
	public List<RoleOptions> getRoleOptions(String userType);
	public List<UserPermissionDto> getRolePermissionByRoleCode(Long roleSeqNo);
	public List<RoleEntity> getAllRoles();
	public List<RoleEntity> queryRoles(QueryRoleRequest model);
	public void saveRole(RoleResp model);
}

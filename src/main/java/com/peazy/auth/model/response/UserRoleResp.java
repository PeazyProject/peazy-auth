package com.peazy.auth.model.response;

import java.util.List;

import lombok.Data;

@Data
public class UserRoleResp {

	private Long roleSeqNo;
	private String roleCode;
	private String roleName;
	private List<UserPermission> permissions;
	private List<UserDataScopeResp> scopes;
	private List<RoleScopeColumnMappingResp> roleScopeColumnMappings;
	
}

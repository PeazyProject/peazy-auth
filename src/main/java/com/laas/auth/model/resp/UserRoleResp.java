package com.laas.auth.model.resp;

import java.util.List;

public class UserRoleResp {
	private Long roleSeqNo;
	private String roleCode;
	private String roleName;
	private List<UserPermission> permissions;
	private List<UserDataScopeResp> scopes;
	
	public Long getRoleSeqNo() {
		return roleSeqNo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleSeqNo(Long roleSeqNo) {
		this.roleSeqNo = roleSeqNo;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<UserPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<UserPermission> permissions) {
		this.permissions = permissions;
	}
	public List<UserDataScopeResp> getScopes() {
		return scopes;
	}
	public void setScopes(List<UserDataScopeResp> scopes) {
		this.scopes = scopes;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}

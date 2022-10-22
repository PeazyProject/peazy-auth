package com.laas.auth.model.args;

public class QueryRoleRequest {
	private String userType;
	private String roleCode;
	private String roleName;
	
	public String getUserType() {
		return userType;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

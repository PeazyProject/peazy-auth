package com.laas.auth.model.args;

import java.util.List;

import com.laas.auth.enumerate.RoleCodeEnum;

public class QueryUserRequest {
	private String account;
	private String userName;
	private String email;
	private String userType;
	/**
	 * {@link RoleCodeEnum}
	 */
	private String roleCode;
	private List<DataScope> dataScopes;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<DataScope> getDataScopes() {
		return dataScopes;
	}
	public void setDataScopes(List<DataScope> dataScopes) {
		this.dataScopes = dataScopes;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}

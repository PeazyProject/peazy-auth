package com.laas.auth.model.resp;

import java.util.List;

import com.laas.auth.model.args.UserRoleScopeUpdateRequest;

public class UserScopeResponse {
	private String uuid;
	private String account;
	private String email;
	private String userName;
	private String userType;
	private Integer isActivated;
	private String employCode;
	private List<UserRoleScopeUpdateRequest> roleScopes;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<UserRoleScopeUpdateRequest> getRoleScopes() {
		return roleScopes;
	}
	public void setRoleScopes(List<UserRoleScopeUpdateRequest> roleScopes) {
		this.roleScopes = roleScopes;
	}
	public Integer getIsActivated() {
		return isActivated;
	}
	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}
	public String getEmployCode() {
		return employCode;
	}
	public void setEmployCode(String employCode) {
		this.employCode = employCode;
	}
}

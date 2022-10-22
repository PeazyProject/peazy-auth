package com.laas.auth.model.resp;

public class RoleResp {
	private Long seqNo;
	private String roleCode;
	private String roleName;
	private String userType;
	private Integer isActivated;
	private String currentUser;
	
	public Long getSeqNo() {
		return seqNo;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getUserType() {
		return userType;
	}
	public Integer getIsActivated() {
		return isActivated;
	}
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}

	public String getCurrentUser() {
		return this.currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

}

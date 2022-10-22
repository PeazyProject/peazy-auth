package com.laas.auth.model.args;

public class RolePermissionRequest {
	private Long permissionSeqNo;
	private String permissionCode;
	private String permissionName;
	
	public Long getPermissionSeqNo() {
		return permissionSeqNo;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionSeqNo(Long permissionSeqNo) {
		this.permissionSeqNo = permissionSeqNo;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
}

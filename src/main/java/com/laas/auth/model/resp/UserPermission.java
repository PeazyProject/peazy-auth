package com.laas.auth.model.resp;

public class UserPermission {
	private Long permissionSeqNo;
	private String permissionCode;
	private String permissionName;
	
	public Long getPermissionSeqNo() {
		return permissionSeqNo;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionSeqNo(Long permissionSeqNo) {
		this.permissionSeqNo = permissionSeqNo;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionCode() {
		return permissionCode;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	
}

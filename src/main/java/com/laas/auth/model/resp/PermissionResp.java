package com.laas.auth.model.resp;

public class PermissionResp {
	private Long permissionSeqNo;
	private String permissionCode;
	private String permissionName;
	
	public String getPermissionCode() {
		return permissionCode;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public Long getPermissionSeqNo() {
		return permissionSeqNo;
	}
	public void setPermissionSeqNo(Long permissionSeqNo) {
		this.permissionSeqNo = permissionSeqNo;
	}
}

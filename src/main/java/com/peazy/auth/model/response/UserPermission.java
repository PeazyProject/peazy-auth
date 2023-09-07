package com.peazy.auth.model.response;

import lombok.Data;

@Data
public class UserPermission {
	private Long permissionSeqNo;
	private String permissionCode;
	private String permissionName;	
}

package com.peazy.auth.model.response;

import lombok.Data;

@Data
public class UserDataScopeResp {
	private String tenantCode;
	private String orgId;
	private String dept;
	private String brand;
	private String customer;
	private String facilityCode;
	private String forwarderCode;
}

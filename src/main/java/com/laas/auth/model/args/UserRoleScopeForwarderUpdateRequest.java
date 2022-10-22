package com.laas.auth.model.args;

public class UserRoleScopeForwarderUpdateRequest {
	private String forwarderCode;
	private String tenantCode;
	private String facility;
	
	public String getForwarderCode() {
		return forwarderCode;
	}
	public String getTenantCode() {
		return tenantCode;
	}
	public String getFacility() {
		return facility;
	}
	public void setForwarderCode(String forwarderCode) {
		this.forwarderCode = forwarderCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
}

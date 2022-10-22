package com.laas.auth.model.resp;

public class UserDataScopeResp {
	private String tenantCode;
	private String orgId;
	private String dept;
	private String brand;
	private String customer;
	private String facilityCode;
	private String forwarderCode;
	
	public String getTenantCode() {
		return tenantCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public String getDept() {
		return dept;
	}
	public String getBrand() {
		return brand;
	}
	public String getCustomer() {
		return customer;
	}
	public String getFacilityCode() {
		return facilityCode;
	}
	public String getForwarderCode() {
		return forwarderCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	public void setForwarderCode(String forwarderCode) {
		this.forwarderCode = forwarderCode;
	}
}

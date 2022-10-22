package com.laas.auth.model.args;

public class DataScope {
	private String tenant;
	private String dept;
	private String customer;
	private String facility;
	private String orgId; 
    private String brand;
    private String forwarder;
	
	public String getTenant() {
		return tenant;
	}
	public String getDept() {
		return dept;
	}
	public String getCustomer() {
		return customer;
	}
	public String getFacility() {
		return facility;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getOrgId() {
		return orgId;
	}
	public String getBrand() {
		return brand;
	}
	public String getForwarder() {
		return forwarder;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public void setForwarder(String forwarder) {
		this.forwarder = forwarder;
	}
}

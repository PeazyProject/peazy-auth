package com.laas.auth.model.args;

public class UserRoleScopeTenantUpdateRequest {
	private String tenantCode;
	private String orgId;
	private String dept;
	private String customer;
	private String brand;
	private String facility;
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
    public String getFacility() {
        return facility;
    }
    public void setFacility(String facility) {
        this.facility = facility;
    }
    
    @Override
    public String toString() {
        return "UserRoleScopeTenantUpdateRequest [tenantCode=" + tenantCode + ", orgId=" + orgId
                + ", dept=" + dept + ", customer=" + customer + ", brand=" + brand + ", facility="
                + facility + "]";
    }
	
}

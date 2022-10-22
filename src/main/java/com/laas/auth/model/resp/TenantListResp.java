package com.laas.auth.model.resp;

public class TenantListResp {

	private String tenantCode;
	private String tenantName;
	private Integer isActivated;
	private String contactPhone;
	private String contactEmail;
	private String contactName;
	private String contactAddress;
	private String contactZipcode;
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public Integer getIsActivated() {
		return isActivated;
	}
	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getContactZipcode() {
		return contactZipcode;
	}
	public void setContactZipcode(String contactZipcode) {
		this.contactZipcode = contactZipcode;
	}

}

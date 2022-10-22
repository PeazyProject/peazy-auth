package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tenant", schema = "Auth")
public class TenantEntity {

	private Long seqNo;
	private String tenantCode;
	private String tenantName;
	private Integer isActivated;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;

	private String contactPhone;
	private String contactEmail;
	private String contactName;
	private String contactAddress;
	private String contactZipcode;
	private String authKey;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeqNo", unique = true, nullable = false)
	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
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
	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

}

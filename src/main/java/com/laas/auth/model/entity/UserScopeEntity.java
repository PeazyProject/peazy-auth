package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[UserScope]", schema = "Auth")
public class UserScopeEntity {
	private Long seqNo;
	private String userUUID;
	private Long roleSeqNo;
	private String tenantCode;
	private String orgId;
	private String dept;
	private String brand;
	private String customer;
	private String facilityCode;
	private String forwarderCode;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeqNo", unique = true, nullable = false)
	public Long getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	public Long getRoleSeqNo() {
		return roleSeqNo;
	}
	public void setRoleSeqNo(Long roleSeqNo) {
		this.roleSeqNo = roleSeqNo;
	}
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	public String getForwarderCode() {
		return forwarderCode;
	}
	public void setForwarderCode(String forwarderCode) {
		this.forwarderCode = forwarderCode;
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
}

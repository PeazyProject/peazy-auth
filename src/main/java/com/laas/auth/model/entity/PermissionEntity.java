package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[Permission]", schema = "Auth")
public class PermissionEntity {
	private Long seqNo;
	private String permissionCode;
	private String permissionName;
	private Integer isActivated;
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
	public String getPermissionCode() {
		return permissionCode;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public Integer getIsActivated() {
		return isActivated;
	}
	public String getCreateUser() {
		return createUser;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public void setIsActivated(Integer isActivated) {
		this.isActivated = isActivated;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
}

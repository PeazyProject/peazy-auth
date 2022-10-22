package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[RolePermission]", schema = "Auth")
public class RolePermissionEntity {
	private Long seqNo;
	private Long roleSeqNo;
	private Long permissionSeqNo;
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
	public Long getRoleSeqNo() {
		return roleSeqNo;
	}
	public Long getPermissionSeqNo() {
		return permissionSeqNo;
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
	public void setRoleSeqNo(Long roleSeqNo) {
		this.roleSeqNo = roleSeqNo;
	}
	public void setPermissionSeqNo(Long permissionSeqNo) {
		this.permissionSeqNo = permissionSeqNo;
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

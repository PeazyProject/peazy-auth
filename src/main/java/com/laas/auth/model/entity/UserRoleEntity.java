package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[UserRole]", schema = "Auth")
public class UserRoleEntity {
	private Long seqNo;
	private String userUUID;
	private Long roleSeqNo;
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
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	
}

package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[Role]", schema = "Auth")
public class RoleEntity {
	private Long seqNo;
	private String roleCode;
	private String roleName;
	private String userType;
	private Integer isActivated;
	private String createUser;
	private Date CreateDt;
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
		return CreateDt;
	}
	public void setCreateDt(Date createDt) {
		CreateDt = createDt;
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

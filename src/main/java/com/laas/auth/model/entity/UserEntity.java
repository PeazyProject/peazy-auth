package com.laas.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "[User]", schema = "Auth")
public class UserEntity {

	@Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;
	private String loginAccount;
	private String loginSecretCode;
	private String userName;
	private String userType;

	private String email;

	private String status;

	private Integer errorCnt;

	private Date lastFailLoginDt;

	private Date lastChangePxdDt;

	private Date lastLoginDt;

	private Date lastLogoutDt;

	private String createUser;

	private Date createDt;

	private String updateUser;

	private Date updateDt;
	
	private Integer isActivated;
	
	private String employCode;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public String getLoginSecretCode() {
		return loginSecretCode;
	}

	public void setLoginSecretCode(String loginSecretCode) {
		this.loginSecretCode = loginSecretCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getErrorCnt() {
		return errorCnt;
	}

	public void setErrorCnt(Integer errorCnt) {
		this.errorCnt = errorCnt;
	}

	public Date getLastFailLoginDt() {
		return lastFailLoginDt;
	}

	public void setLastFailLoginDt(Date lastFailLoginDt) {
		this.lastFailLoginDt = lastFailLoginDt;
	}

	public Date getLastChangePxdDt() {
		return lastChangePxdDt;
	}

	public void setLastChangePxdDt(Date lastChangePxdDt) {
		this.lastChangePxdDt = lastChangePxdDt;
	}

	public Date getLastLoginDt() {
		return lastLoginDt;
	}

	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	public Date getLastLogoutDt() {
		return lastLogoutDt;
	}

	public void setLastLogoutDt(Date lastLogoutDt) {
		this.lastLogoutDt = lastLogoutDt;
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

    public String getEmployCode() {
        return employCode;
    }

    public void setEmployCode(String employCode) {
        this.employCode = employCode;
    }

}

package com.peazy.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "Auth_User", schema = "alanlee")
@Data
public class UserEntity {
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "UserUUID", unique = true, nullable = false)
	private String userUUid;
	private String userAccount;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userPhoneNumber;
	private String userStoreName;
	private String userAddress;
	private String loginErrorCnt;
	private String activatedStatus;
	private String isFinishWholesale;
	private String isPaidDeposit;
	private String userType;
	private Date lastLoginDt;
	private Date lastLogoutDt;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;
}

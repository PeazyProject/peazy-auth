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
	@Column(name = "UUID", unique = true, nullable = false)
	private String uuid;
	private String account;
	private String name;
	private String password;
	private String email;
	private String phoneNumber;
	private String storeName;
	private String address;
	private String loginErrorCnt;
	private String activatedStatus;
	private String isFinishWholesale;
	private String isPaidDeposit;
	private String userType;
	private Date lastLoginDt;
	private Date lastLogoutDt;
	private String isInvoice;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;
}

package com.peazy.auth.model.response;

import java.util.List;

import lombok.Data;

@Data
public class UserProfile {

	private String account;
	private String name;
	private String email;
	private String phoneNumber;
	private String storeName;
	private String address;
	private String loginErrorCnt;
	private String activatedStatus;
	private String isFinishWholesale;
	private String isPaidDeposit;
	private String userType;
	private List<UserRoleResp> roles;
	private String isInvoiice;

}

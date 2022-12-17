package com.peazy.auth.model.response;

import lombok.Data;

@Data
public class UserProfile {

	private String userAccount;
	private String userName;
	private String userEmail;
	private String userPhoneNumber;
	private String userStoreName;
	private String userAddress;
	private String loginErrorCnt;
	private String activatedStatus;
	private String isFinishWholesale;
	private String isPaidDeposit;
	private String userType;
	private String isInvoiice;

}

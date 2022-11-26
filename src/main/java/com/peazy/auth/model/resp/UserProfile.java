package com.peazy.auth.model.resp;

import java.util.Date;

import lombok.Data;

@Data
public class UserProfile {
	private String uuid;
	private String loginAccount;
	private String userName;
	private String email;
	private String status;
	private String userType;
	private Integer errorCnt;
	private Date lastFailLoginDt;
	private Date lastChangePxdDt;
	private Date lastLoginDt;
	private Date lastLogoutDt;
	private String employCode;
}

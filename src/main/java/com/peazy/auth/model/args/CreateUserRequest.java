package com.peazy.auth.model.args;

import lombok.Data;

@Data
public class CreateUserRequest {
	private String userAccount;
    private String userName;
    private String userPassword;
    private String userEmail;
	private String userType;
}

package com.peazy.auth.model.args;

import java.util.Date;

import lombok.Data;

@Data
public class CreateCustomerUserRequest {
    private String userAccount;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhoneNumber;
    private String userStoreName;
    private String userAddress;
    private Date createDt;
}

package com.peazy.auth.model.args;

import java.util.Date;

import lombok.Data;

@Data
public class CreateSupplierUserRequest {
    private String userAccount;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Date createDt;
}

package com.peazy.auth.model.args;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = -4759388262050468119L;
    @ApiModelProperty(example = "testaccount1")
    private String userName;
    @ApiModelProperty(example = "12345")
    private String password;
    private String userEmail;
   
    // need default constructor for JSON Parsing
    public JwtRequest() {

    }

    public JwtRequest(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public JwtRequest(String userName, String password, String userEmail) {
	    this.setUserName(userName);
	    this.setPassword(password);
        this.setUserEmail(userEmail);
	}
}
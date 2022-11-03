package com.peazy.auth.model.args;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = -4759388262050468119L;
    @ApiModelProperty(example = "JayAccount1")
    private String userName;
    @ApiModelProperty(example = "password")
    private String userPassword;
    @ApiModelProperty(example = "Jay@gmail.com")
    private String userEmail;
   
    // need default constructor for JSON Parsing
    public JwtRequest() {

    }

    public JwtRequest(String userName, String userPassword) {
        this.setUserName(userName);
        this.setUserPassword(userPassword);
    }

    public JwtRequest(String userName, String userPassword, String userEmail) {
	    this.setUserName(userName);
	    this.setUserPassword(userPassword);
        this.setUserEmail(userEmail);
	}
}
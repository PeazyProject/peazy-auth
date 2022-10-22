package com.laas.auth.model.resp;

import lombok.Data;

@Data
public class JwtUserResponse {

    private String uuid;
    private String userName;
    private String email;
    private String loginFacility;
    private String loginLanguage;
    private String userType;

}

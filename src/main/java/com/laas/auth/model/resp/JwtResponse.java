package com.laas.auth.model.resp;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2834409284828426814L;
	private final String jwttoken;
	private  String redirectUrl;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
	public String toString() {
		return "JwtResponse [jwttoken=" + jwttoken + ", redirectUrl=" + redirectUrl + "]";
	}
	
}
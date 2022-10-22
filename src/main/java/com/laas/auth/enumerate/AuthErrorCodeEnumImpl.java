package com.laas.auth.enumerate;

public enum AuthErrorCodeEnumImpl implements ErrorCodeEunm {

	WH_OUTBOUND_ERROR("999");
	
	private String code;

	private AuthErrorCodeEnumImpl(String errorCode) {
		this.code = errorCode;
	}

	@Override
	public String getCategory() {
		return "WHOUTBOUND";
	}

	@Override
	public String getCode() {
		return this.code;
	}

}

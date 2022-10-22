package com.laas.auth.exception;

import com.laas.auth.enumerate.ErrorCodeEunm;

public class ErrorCodeException extends RuntimeException {

	private static final long serialVersionUID = -8932595907926333782L;

	private ErrorCodeEunm errorCode;

	public ErrorCodeException(ErrorCodeEunm errorCodeEunm) {

		this.errorCode = errorCodeEunm;
	}

	public ErrorCodeEunm getErrorCode() {
		return errorCode;
	}

}
package com.peazy.auth.model.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -2834409284828426814L;
	private final String jwtToken;
}
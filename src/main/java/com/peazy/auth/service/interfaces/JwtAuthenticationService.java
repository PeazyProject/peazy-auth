package com.peazy.auth.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.peazy.auth.model.response.AuthorizationResponse;

public interface JwtAuthenticationService {

    ResponseEntity<AuthorizationResponse> authorization(String authorization) throws Exception;

}

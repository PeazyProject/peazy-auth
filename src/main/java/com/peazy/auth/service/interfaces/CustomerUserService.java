package com.peazy.auth.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.args.CreateCustomerUserRequest;
import com.peazy.auth.model.entity.CustomerUserEntity;

public interface CustomerUserService {
	public List<CustomerUserEntity> getUsers() throws JsonProcessingException;

	public void createCustomerUser(@RequestBody CreateCustomerUserRequest request);

	public Optional<CustomerUserEntity> findByUserName(String username);
}

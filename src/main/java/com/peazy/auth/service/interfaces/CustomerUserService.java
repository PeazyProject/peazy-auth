package com.peazy.auth.service.interfaces;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.entity.CustomerUserEntity;

public interface CustomerUserService {
	public List<CustomerUserEntity> getUsers() throws JsonProcessingException;
}

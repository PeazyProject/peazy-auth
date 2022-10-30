package com.peazy.auth.service.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.args.CreateSupplierUserRequest;
import com.peazy.auth.model.entity.SupplierUserEntity;

public interface SupplierUserService {
	public List<SupplierUserEntity> getUsers() throws JsonProcessingException;

	public void createSupplierUser(@RequestBody CreateSupplierUserRequest request);
}

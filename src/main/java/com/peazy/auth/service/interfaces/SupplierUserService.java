package com.peazy.auth.service.interfaces;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.args.ChangePasswordRequest;
import com.peazy.auth.model.args.QueryUserRequest;
import com.peazy.auth.model.dto.QueryUserDto;

public interface SupplierUserService {
	public List<QueryUserDto> getUsers(QueryUserRequest model) throws JsonProcessingException;
	void changePassword(ChangePasswordRequest model);
}

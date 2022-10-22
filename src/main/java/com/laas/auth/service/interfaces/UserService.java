package com.laas.auth.service.interfaces;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laas.auth.model.args.ChangePasswordRequest;
import com.laas.auth.model.args.QueryUserRequest;
import com.laas.auth.model.args.UserUpdateRequest;
import com.laas.auth.model.dto.QueryUserDto;
import com.laas.auth.model.resp.UserScopeResponse;

public interface UserService {
	public List<QueryUserDto> getUsers(QueryUserRequest model) throws JsonProcessingException;
	public String updateUserRoleScope(UserUpdateRequest model);
	public String insertUserRoleScope(UserUpdateRequest model);
	public UserScopeResponse getUserScope(String userId);
	void changePassword(ChangePasswordRequest model);
}

package com.laas.auth.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laas.auth.model.entity.UserScopeEntity;
import com.laas.auth.repository.UserScopeRepository;
import com.laas.auth.service.interfaces.UserScopeService;

@Service
public class UserScopeServiceImp implements UserScopeService {
	@Autowired
	private UserScopeRepository userScopeRepository;

	@Override
	public List<UserScopeEntity> getScopesByRoleAndUser(String userId, Long roleSeqNo) {
		List<UserScopeEntity> scopes = userScopeRepository.findByUserUUIDAndRoleSeqNo(userId, roleSeqNo);
		return scopes;
	}
}

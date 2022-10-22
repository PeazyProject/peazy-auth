package com.laas.auth.service.interfaces;

import java.util.List;

import com.laas.auth.model.entity.UserScopeEntity;

public interface UserScopeService {
	List<UserScopeEntity> getScopesByRoleAndUser(String userId, Long roleSeqNo);
}

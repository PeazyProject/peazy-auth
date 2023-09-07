package com.peazy.auth.service.interfaces;

import java.util.List;

import com.peazy.auth.model.entity.UserScopeEntity;

public interface UserScopeService {
	List<UserScopeEntity> getScopesByRoleAndUser(String userId, Long roleSeqNo);
    // List<UserInfo> authUserScopeFilter(AuthUserScopeFilterRequest orderInfo);
}

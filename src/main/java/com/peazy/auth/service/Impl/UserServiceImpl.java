package com.peazy.auth.service.Impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.enumerate.UserErrorCodeEnumImpl;
import com.peazy.auth.exception.ErrorCodeException;
import com.peazy.auth.model.args.ChangePasswordRequest;
import com.peazy.auth.model.args.QueryUserRequest;
import com.peazy.auth.model.dto.QueryUserDto;
import com.peazy.auth.model.entity.UserEntity;
import com.peazy.auth.repository.UserRepository;
import com.peazy.auth.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<QueryUserDto> getUsers(QueryUserRequest model) throws JsonProcessingException {
		List<QueryUserDto> result = null;
		List<UserEntity> userEntities = userRepository.findAll();
		System.out.println("LOOK userEntities = " + userEntities);
		return result;
	}

	@Override
	public void changePassword(ChangePasswordRequest model) {
		UserEntity userEntity = userRepository.findByuserUUid(model.getUuid()).orElse(null);

		if (userEntity != null) {
			// boolean isSame = bcryptEncoder.matches(model.getOldPassword(), userEntity.getUserPassword());
			// if (!isSame) {
			// 	throw new ErrorCodeException(UserErrorCodeEnumImpl.PASSWORD_NOT_SAME);
			// }

			// userEntity.setUserPassword(bcryptEncoder.encode(model.getNewPassword()));
			// userEntity.setUpdateDt(new Date());
			userEntity.setUpdateUser(model.getLoginAccount());
			userRepository.save(userEntity);

		} else {
			throw new ErrorCodeException(UserErrorCodeEnumImpl.USER_NOT_FOUND);
		}

	}

	

}

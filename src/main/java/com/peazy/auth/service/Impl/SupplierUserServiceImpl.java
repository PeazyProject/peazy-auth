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
import com.peazy.auth.model.entity.SupplierUserEntity;
import com.peazy.auth.repository.SupplierUserRepository;
import com.peazy.auth.service.interfaces.SupplierUserService;

@Service
public class SupplierUserServiceImpl implements SupplierUserService {

	@Autowired
	private SupplierUserRepository supplierUserRepository;

	@Override
	public List<QueryUserDto> getUsers(QueryUserRequest model) throws JsonProcessingException {
		List<QueryUserDto> result = null;
		List<SupplierUserEntity> userEntities = supplierUserRepository.findAll();
		System.out.println("LOOK userEntities = " + userEntities);
		return result;
	}

	@Override
	public void changePassword(ChangePasswordRequest model) {
		// CustomerUserEntity userEntity = customerUserRepository.findByuserUUid(model.getUuid()).orElse(null);

		// if (userEntity != null) {
		// 	// boolean isSame = bcryptEncoder.matches(model.getOldPassword(), userEntity.getUserPassword());
		// 	// if (!isSame) {
		// 	// 	throw new ErrorCodeException(UserErrorCodeEnumImpl.PASSWORD_NOT_SAME);
		// 	// }

		// 	// userEntity.setUserPassword(bcryptEncoder.encode(model.getNewPassword()));
		// 	// userEntity.setUpdateDt(new Date());
		// 	userEntity.setUpdateUser(model.getLoginAccount());
		// 	customerUserRepository.save(userEntity);

		// } else {
		// 	throw new ErrorCodeException(UserErrorCodeEnumImpl.USER_NOT_FOUND);
		// }

	}

	

}

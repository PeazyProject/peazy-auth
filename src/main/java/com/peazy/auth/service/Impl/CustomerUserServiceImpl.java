package com.peazy.auth.service.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.args.CreateCustomerUserRequest;
import com.peazy.auth.model.entity.CustomerUserEntity;
import com.peazy.auth.repository.CustomerUserRepository;
import com.peazy.auth.service.interfaces.CustomerUserService;

@Service
public class CustomerUserServiceImpl implements CustomerUserService {

	@Autowired
	private CustomerUserRepository customerUserRepository;

	@Override
	public List<CustomerUserEntity> getUsers() throws JsonProcessingException {
		List<CustomerUserEntity> userEntities = customerUserRepository.findAll();
		return userEntities;
	}

	@Override
	public void createCustomerUser(@RequestBody CreateCustomerUserRequest request) {
		CustomerUserEntity customerUserEntity = new CustomerUserEntity();
		BeanUtils.copyProperties(request, customerUserEntity);
		customerUserEntity.setLoginErrorCnt("0");
		customerUserEntity.setActivatedStatus("0");
		customerUserEntity.setIsFinishWholesale("0");
		customerUserEntity.setIsPaidDeposit("0");
		customerUserEntity.setCreateUser(request.getUserName());
		customerUserEntity.setUpdateUser(request.getUserName());
		customerUserEntity.setUpdateDt(request.getCreateDt());
		customerUserRepository.save(customerUserEntity);
	}

}

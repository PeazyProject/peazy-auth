package com.peazy.auth.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
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

}

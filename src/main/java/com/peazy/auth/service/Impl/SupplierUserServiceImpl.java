package com.peazy.auth.service.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.args.CreateSupplierUserRequest;
import com.peazy.auth.model.entity.SupplierUserEntity;
import com.peazy.auth.repository.SupplierUserRepository;
import com.peazy.auth.service.interfaces.SupplierUserService;

@Service
public class SupplierUserServiceImpl implements SupplierUserService {

	@Autowired
	private SupplierUserRepository supplierUserRepository;

	@Override
	public List<SupplierUserEntity> getUsers() throws JsonProcessingException {
		List<SupplierUserEntity> userEntities = supplierUserRepository.findAll();
		return userEntities;
	}

	@Override
	public void createSupplierUser(@RequestBody CreateSupplierUserRequest request) {
		SupplierUserEntity supplierUserEntity = new SupplierUserEntity();
		BeanUtils.copyProperties(request, supplierUserEntity);
		supplierUserEntity.setIsActivited("0");
		supplierUserEntity.setCreateUser(request.getUserName());
		supplierUserEntity.setUpdateUser(request.getUserName());
		supplierUserEntity.setUpdateDt(request.getCreateDt());
		supplierUserRepository.save(supplierUserEntity);
	}

}

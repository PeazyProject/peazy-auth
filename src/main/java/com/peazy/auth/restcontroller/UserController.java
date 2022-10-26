package com.peazy.auth.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.args.ChangePasswordRequest;
import com.peazy.auth.model.args.QueryUserRequest;
import com.peazy.auth.model.dto.QueryUserDto;
import com.peazy.auth.model.entity.CustomerUserEntity;
import com.peazy.auth.model.entity.SupplierUserEntity;
import com.peazy.auth.service.interfaces.CustomerUserService;
import com.peazy.auth.service.interfaces.SupplierUserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerUserService customerUserService;

	@Autowired
	private SupplierUserService supplierUserService;

	@PostMapping(value = "/getCustomerUsers")
	public ResponseEntity<List<CustomerUserEntity>> getCustomerUsers() throws JsonProcessingException {
		List<CustomerUserEntity> customerUserList = customerUserService.getUsers();
		return ResponseEntity.ok(customerUserList);
	}

	@PostMapping(value = "/getSupplierUsers")
	public ResponseEntity<List<SupplierUserEntity>> getSupplierUsers() throws JsonProcessingException {
		List<SupplierUserEntity> supplierUserList = supplierUserService.getUsers();
		return ResponseEntity.ok(supplierUserList);
	}

}

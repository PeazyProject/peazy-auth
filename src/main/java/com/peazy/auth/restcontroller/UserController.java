package com.peazy.auth.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peazy.auth.model.entity.UserEntity;
import com.peazy.auth.service.interfaces.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@PostMapping(value = "/getUser")
	public ResponseEntity<List<UserEntity>> getUser() throws JsonProcessingException {
		List<UserEntity> userList = userService.getUser();
		return ResponseEntity.ok(userList);
	}

}

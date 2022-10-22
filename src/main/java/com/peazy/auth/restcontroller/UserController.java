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
import com.peazy.auth.service.interfaces.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "/getUsers")
	public ResponseEntity<List<QueryUserDto>> getUsers(@RequestBody QueryUserRequest model) throws JsonProcessingException {
		List<QueryUserDto> result = userService.getUsers(model);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest model) {
		userService.changePassword(model);
		return ResponseEntity.ok(null);
	}

}

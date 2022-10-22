package com.laas.auth.restcontroller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.laas.auth.model.args.ChangePasswordRequest;
import com.laas.auth.model.args.QueryUserRequest;
import com.laas.auth.model.args.UserUpdateRequest;
import com.laas.auth.model.dto.QueryUserDto;
import com.laas.auth.model.resp.UserScopeResponse;
import com.laas.auth.service.interfaces.UserService;

@CrossOrigin
@RestController
@RequestMapping(path = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	@PostMapping(value = "")
	public ResponseEntity<List<QueryUserDto>> getUsers(@RequestBody QueryUserRequest model) throws JsonProcessingException {
		List<QueryUserDto> result = userService.getUsers(model);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/userRoleScope/{userId}")
	public ResponseEntity<UserScopeResponse> getUserRoleScope(@PathVariable String userId) {
		UserScopeResponse response = userService.getUserScope(userId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/userRoleScope")
	public ResponseEntity<HashMap<String, String>> insertUserRoleScope(@RequestBody UserUpdateRequest model) {
		String uuid = userService.insertUserRoleScope(model);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", uuid);
		return ResponseEntity.ok(map);
	}
	
	@PutMapping(value = "/userRoleScope")
	public ResponseEntity<HashMap<String, String>> updateUserRoleScope(@RequestBody UserUpdateRequest model) {
		String uuid = userService.updateUserRoleScope(model);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userId", uuid);
		return ResponseEntity.ok(map);
	}
	
	@PostMapping(value = "/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest model) {
		userService.changePassword(model);
		return ResponseEntity.ok(null);
	}

}

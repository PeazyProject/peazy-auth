package com.laas.auth.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laas.auth.model.args.QueryRoleRequest;
import com.laas.auth.model.dto.UserPermissionDto;
import com.laas.auth.model.entity.RoleEntity;
import com.laas.auth.model.resp.RoleOptions;
import com.laas.auth.model.resp.RoleResp;
import com.laas.auth.service.interfaces.RoleService;

@CrossOrigin
@RestController
@RequestMapping(path = "/role",produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(value = "/roleOption/{userType}")
	public ResponseEntity<List<RoleOptions>> getRoleOptions(@PathVariable String userType) {
		List<RoleOptions> result = roleService.getRoleOptions(userType);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/rolePermission/{roleSeqNo}")
	public ResponseEntity<List<UserPermissionDto>> getRolePermissionByRoleCode(@PathVariable Long roleSeqNo){
		List<UserPermissionDto> result = roleService.getRolePermissionByRoleCode(roleSeqNo);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/roles")
	public ResponseEntity<List<RoleResp>> queryRoles(@RequestBody QueryRoleRequest model) {
		List<RoleEntity> roles = roleService.queryRoles(model);
		List<RoleResp> result = roles.stream().map(x -> {
			RoleResp res = new RoleResp();
			BeanUtils.copyProperties(x, res);
			return res;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<?> saveRoles(@RequestBody RoleResp model) {
		roleService.saveRole(model);
		return ResponseEntity.ok(model);
	}
}

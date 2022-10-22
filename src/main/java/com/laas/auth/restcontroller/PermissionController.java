package com.laas.auth.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.laas.auth.model.args.RolePermissionRequest;
import com.laas.auth.model.entity.PermissionEntity;
import com.laas.auth.model.resp.PermissionResp;
import com.laas.auth.service.interfaces.PermissionService;

@CrossOrigin
@RestController
@RequestMapping(path = "/permission",produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping(value = "")
	public ResponseEntity<List<PermissionResp>> getRoleOptions() {
		List<PermissionEntity> result = permissionService.getAllPermission();
		
		List<PermissionResp> resp = result.stream().map(x -> {
			PermissionResp res = new PermissionResp();
			res.setPermissionSeqNo(x.getSeqNo());
			res.setPermissionCode(x.getPermissionCode());
			res.setPermissionName(x.getPermissionName());
			return res;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping(value = "/{roleCode}/{currentUser}")
	public ResponseEntity<?> modifyRolePermission(@PathVariable Long roleCode, @PathVariable String currentUser, @RequestBody List<RolePermissionRequest> model) {	
		permissionService.modifyRolePermission(roleCode, currentUser, model);
		return ResponseEntity.ok(null);
	}
}

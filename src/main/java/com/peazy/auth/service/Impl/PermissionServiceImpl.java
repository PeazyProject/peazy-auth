package com.peazy.auth.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peazy.auth.model.dto.UserPermissionDto;
import com.peazy.auth.model.entity.PermissionEntity;
import com.peazy.auth.repository.PermissionRepository;
import com.peazy.auth.repository.RolePermissionRepository;
import com.peazy.auth.service.interfaces.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public List<UserPermissionDto> getPermissionByRole(Long roleSeqNo) {
		List<UserPermissionDto> permissions = rolePermissionRepository.findPermissionByRole(roleSeqNo);
		return permissions;
	}

	@Override
	public List<PermissionEntity> getAllPermission() {
		List<PermissionEntity> result = permissionRepository.findByIsActivated(1);
		return result;
	}

	// @Transactional
	// @Override
	// public void modifyRolePermission(Long roleSeqNo,String currentUser, List<RolePermissionRequest> model) {
	// 	List<RolePermissionEntity> saveEntities = model.stream().map(x -> {
	// 		RolePermissionEntity entity = new RolePermissionEntity();
			
	// 		entity.setRoleSeqNo(roleSeqNo);
	// 		entity.setPermissionSeqNo(x.getPermissionSeqNo());
	// 		entity.setIsActivated(1);
	// 		entity.setCreateUser(currentUser);
	// 		entity.setCreateDt(new Date());
	// 		entity.setUpdateUser(currentUser);
	// 		entity.setUpdateDt(new Date());
			
	// 		return entity;
	// 	}).collect(Collectors.toList());
		
	// 	if(roleSeqNo != null) {
	// 		rolePermissionRepository.deleteByRoleSeqNo(roleSeqNo);
	// 		rolePermissionRepository.saveAll(saveEntities);	
	// 	}
	// }
	

}

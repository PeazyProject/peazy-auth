package com.laas.auth.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laas.auth.model.args.QueryRoleRequest;
import com.laas.auth.model.dto.UserPermissionDto;
import com.laas.auth.model.entity.RoleEntity;
import com.laas.auth.model.resp.RoleOptions;
import com.laas.auth.model.resp.RoleResp;
import com.laas.auth.repository.RolePermissionRepository;
import com.laas.auth.repository.RoleRepository;
import com.laas.auth.service.interfaces.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Override
	public List<RoleOptions> getRoleOptions(String userType) {

		List<RoleEntity> roles = roleRepository.findByUserTypeAndIsActivated(userType, 1);
		
		List<RoleOptions> result = roles.stream().map(x -> {
			RoleOptions option = new RoleOptions();
			option.setRoleCode(x.getSeqNo());
			option.setRoleName(x.getRoleName());
			return option;
		}).collect(Collectors.toList());
		
		return result;
	}

	@Override
	public List<UserPermissionDto> getRolePermissionByRoleCode(Long roleSeqNo) {
		List<UserPermissionDto> rolePermission = rolePermissionRepository.findPermissionByRole(roleSeqNo);
		return rolePermission;
	}

	@Override
	public List<RoleEntity> getAllRoles() {
		List<RoleEntity> roles = roleRepository.findAll();
		return roles;
	}

	@Override
	public void saveRole(RoleResp model) {
		
		if(model.getSeqNo() != null) {
			RoleEntity updateEntity = roleRepository.findById(model.getSeqNo()).orElse(null);
			if(updateEntity != null) {
				updateEntity.setIsActivated(model.getIsActivated());
				updateEntity.setRoleCode(model.getRoleCode());
				updateEntity.setRoleName(model.getRoleName());
				updateEntity.setUserType(model.getUserType());
				updateEntity.setUpdateUser(model.getCurrentUser());
				updateEntity.setUpdateDt(new Date());
				roleRepository.save(updateEntity);
			}
		}else {
			RoleEntity roleEntity = new RoleEntity();
			BeanUtils.copyProperties(model, roleEntity);
			roleEntity.setCreateUser(model.getCurrentUser());
			roleEntity.setCreateDt(new Date());
			roleEntity.setUpdateUser(model.getCurrentUser());
			roleEntity.setUpdateDt(new Date());
			roleRepository.save(roleEntity);	
		}
	}

	@Override
	public List<RoleEntity> queryRoles(QueryRoleRequest model) {
		return roleRepository.findByFilter(model.getUserType(), model.getRoleCode(), model.getRoleName());
	}
}

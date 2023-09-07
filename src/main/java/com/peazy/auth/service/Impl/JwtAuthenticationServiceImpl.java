package com.peazy.auth.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peazy.auth.repository.UserRepository;
import com.peazy.auth.configuration.JwtTokenUtil;
import com.peazy.auth.model.dto.UserPermissionDto;
import com.peazy.auth.model.dto.UserRoleDto;
import com.peazy.auth.model.entity.UserEntity;
import com.peazy.auth.model.entity.UserScopeEntity;
import com.peazy.auth.model.response.AuthorizationResponse;
import com.peazy.auth.model.response.UserDataScopeResp;
import com.peazy.auth.model.response.UserPermission;
import com.peazy.auth.model.response.UserProfile;
import com.peazy.auth.model.response.UserRoleResp;
import com.peazy.auth.service.interfaces.JwtAuthenticationService;
import com.peazy.auth.service.interfaces.PermissionService;
import com.peazy.auth.service.interfaces.UserRoleService;
import com.peazy.auth.service.interfaces.UserScopeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("JwtAuthenticationService")
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserScopeService userScopeService;

    // @Autowired
    // private RoleScopeColumnMappingRepository roleScopeColumnMappingRepository;

    public ResponseEntity<AuthorizationResponse> authorization(String authorization) throws Exception {
        String token = authorization.substring(7);
		String email = jwtTokenUtil.getUserEmailFromToken(token);
		Optional<UserEntity> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new Exception("can't find user");
		}

        UserProfile profile = new UserProfile();
        BeanUtils.copyProperties(user.get(), profile);
        AuthorizationResponse response = new AuthorizationResponse();

        // get roles
        List<UserRoleDto> roles = userRoleService.getUserRoles(user.get().getUuid());
        List<UserRoleResp> profileRoles = roles.stream().map(x -> {
            UserRoleResp userRoleResp = new UserRoleResp();
            userRoleResp.setRoleName(x.getRoleName());
            userRoleResp.setRoleSeqNo(x.getRoleSeqNo());
            userRoleResp.setRoleCode(x.getRoleCode());
            return userRoleResp;
        }).collect(Collectors.toList());

        profile.setRoles(profileRoles);
        log.info("authorization profile = {}", profile);
        // get permission & scopes
        profile.getRoles().forEach(x -> {
            List<UserPermissionDto> permissions = permissionService.getPermissionByRole(x.getRoleSeqNo());
            List<UserPermission> profilePermission = permissions.stream().map(item -> {
                UserPermission permission = new UserPermission();
                permission.setPermissionName(item.getPermissionName());
                permission.setPermissionSeqNo(item.getPermissionSeqNo());
                permission.setPermissionCode(item.getPermissionCode());
                return permission;
            }).collect(Collectors.toList());
            x.setPermissions(profilePermission);

            List<UserScopeEntity> scopes = userScopeService.getScopesByRoleAndUser(user.get().getUuid(),
                    x.getRoleSeqNo());
            List<UserDataScopeResp> scopesProfile = scopes.stream().map(item -> {
                UserDataScopeResp scope = new UserDataScopeResp();
                // scope.setBrand(item.getBrand());
                // scope.setCustomer(item.getCustomer());
                // scope.setDept(item.getDept());
                // scope.setFacilityCode(item.getFacilityCode());
                // scope.setForwarderCode(item.getForwarderCode());
                // scope.setOrgId(item.getOrgId());
                // scope.setTenantCode(item.getTenantCode());
                return scope;
            }).collect(Collectors.toList());
            x.setScopes(scopesProfile);

            // List<RoleScopeColumnMappingEntity> roleScopeColumnMappingEntities = roleScopeColumnMappingRepository.findByRoleSeqNoAndIsActivated(x.getRoleSeqNo(), 1);
            // if (LaasCollectionUtil.isNotEmpty(roleScopeColumnMappingEntities)) {
            //     List<RoleScopeColumnMappingResp> roleScopeColumnMappingResps = roleScopeColumnMappingEntities.stream().map(entity -> {
            //         RoleScopeColumnMappingResp roleScopeColumnMappingResp = new RoleScopeColumnMappingResp();
            //         roleScopeColumnMappingResp.setScopeColumnName(entity.getScopeColumnName());
            //         roleScopeColumnMappingResp.setFunctionName(entity.getFunctionName());
            //         roleScopeColumnMappingResp.setMappingColumnName(entity.getMappingColumnName());
            //         return roleScopeColumnMappingResp;
            //     }).collect(Collectors.toList());
            //     x.setRoleScopeColumnMappings(roleScopeColumnMappingResps);
            // }
        });
        response.setUserProfile(profile);
        log.info("authorization response = {}", response);

        return ResponseEntity.ok(response);
    }
}

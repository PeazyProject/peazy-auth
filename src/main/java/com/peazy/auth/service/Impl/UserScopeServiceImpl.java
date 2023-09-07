package com.peazy.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peazy.auth.model.entity.UserScopeEntity;
import com.peazy.auth.repository.UserRepository;
import com.peazy.auth.repository.UserScopeRepository;
import com.peazy.auth.service.interfaces.UserScopeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserScopeServiceImpl implements UserScopeService {
    
    @Autowired
    private UserScopeRepository userScopeRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public List<UserScopeEntity> getScopesByRoleAndUser(String userUuid, Long roleSeqNo) {
        List<UserScopeEntity> scopes = userScopeRepository.findByUserUuidAndRoleSeqNo(userUuid, roleSeqNo);
        return scopes;
    }
    
    // @Override
    // public List<UserInfo> authUserScopeFilter(AuthUserScopeFilterRequest request) {
    //     log.info("UserScopeServiceImpl authUserScopeFilterRequest request = {}", LaasStringUtil.toJson(request));
    //     List<UserInfo> filterUserInfo = new ArrayList<>();
        
    //     AuthUserScopeFilterRequest convertSearchRequest = this.convertSearchRequest(request);
        
    //     for (String loginAccount : request.getLoginAccounts()) {
    //         int count = userScopeRepository.countLoginAccountByUserScope(request.getTenant(),
    //             request.getOrgId(), convertSearchRequest.getDept(), convertSearchRequest.getBrand(),
    //             request.getCustomer(), request.getFacilityCode(), request.getForwarderCode(), loginAccount);
    //         if (count > 0) {
    //             UserEntity userEntity = userRepository.findByLoginAccount(loginAccount).orElse(null);
    //             if (userEntity != null) {
    //                 filterUserInfo.add(genUserInfo(userEntity));
    //             }
    //         }
    //     }
    //     log.info("UserScopeServiceImpl authUserScopeFilter filterUserInfo = {}", LaasStringUtil.toJson(filterUserInfo));
        
    //     return filterUserInfo;
    // }

    // private UserInfo genUserInfo(UserEntity userEntity) {
    //     UserInfo userInfo = new UserInfo();
    //     userInfo.setLoginAccount(userEntity.getLoginAccount());
    //     userInfo.setEmail(userEntity.getEmail());
    //     return userInfo;
    // }
    
    // private AuthUserScopeFilterRequest convertSearchRequest(AuthUserScopeFilterRequest request) {
        
    //     AuthUserScopeFilterRequest convertSearchRequest = request;
        
    //     List<String> depts = request.getDept() == null ? new ArrayList<>() : request.getDept();
    //     if (depts.isEmpty()) {
    //         depts.add(null);
    //     }
        
    //     List<String> brands = request.getBrand() == null ? new ArrayList<>() : request.getBrand();
    //     if (brands.isEmpty()) {
    //         brands.add(null);
    //     }
        
    //     List<String> customer = request.getCustomer() == null ? new ArrayList<>() : request.getCustomer();
    //     if (customer.isEmpty()) {
    //         customer.add(null);
    //     }
        
    //     convertSearchRequest.setDept(depts);
    //     convertSearchRequest.setBrand(brands);
    //     convertSearchRequest.setCustomer(customer);
    //     return convertSearchRequest;
    // }
    
}

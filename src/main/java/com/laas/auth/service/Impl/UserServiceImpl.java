package com.laas.auth.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laas.auth.enumerate.UserErrorCodeEnumImpl;
import com.laas.auth.enumerate.UserTypeEnum;
import com.laas.auth.exception.ErrorCodeException;
import com.laas.auth.model.args.ChangePasswordRequest;
import com.laas.auth.model.args.QueryUserRequest;
import com.laas.auth.model.args.UserRoleScopeForwarderUpdateRequest;
import com.laas.auth.model.args.UserRoleScopeTenantUpdateRequest;
import com.laas.auth.model.args.UserRoleScopeUpdateRequest;
import com.laas.auth.model.args.UserUpdateRequest;
import com.laas.auth.model.dto.QueryUserDto;
import com.laas.auth.model.entity.NotifyEventContextEntity;
import com.laas.auth.model.entity.NotifyEventEntity;
import com.laas.auth.model.entity.UserEntity;
import com.laas.auth.model.entity.UserRoleEntity;
import com.laas.auth.model.entity.UserScopeEntity;
import com.laas.auth.model.entity.UserSubscriptionEntity;
import com.laas.auth.model.resp.UserScopeResponse;
import com.laas.auth.repository.MandatorySubscriptionRepository;
import com.laas.auth.repository.NotifyEventContextRepository;
import com.laas.auth.repository.NotifyEventRepository;
import com.laas.auth.repository.RoleRepository;
import com.laas.auth.repository.UserRepository;
import com.laas.auth.repository.UserRoleRepository;
import com.laas.auth.repository.UserScopeRepository;
import com.laas.auth.repository.UserSubscriptionRepository;
import com.laas.auth.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserScopeRepository userScopeRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private NotifyEventContextRepository notifyEventContextRepository;

	@Autowired
	private MandatorySubscriptionRepository mandatorySubscriptionRepository;

	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@Autowired
	private NotifyEventRepository notifyEventRepository;

	@Override
	public List<QueryUserDto> getUsers(QueryUserRequest model) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(model.getDataScopes());
		List<QueryUserDto> result = userRepository.getUsersByFilter(model.getAccount(), model.getUserName(),
				model.getEmail(), json, model.getUserType(), model.getRoleCode());
		return result;
	}

	@Transactional
	@Override
	public String updateUserRoleScope(UserUpdateRequest model) {
		UserEntity userEntity = userRepository.findByUuid(model.getUuid()).orElse(null);
		if (userEntity != null) {
			userEntity.setLoginAccount(model.getAccount());
			userEntity.setUserName(model.getUserName());
			userEntity.setEmail(model.getEmail());
			userEntity.setIsActivated(model.getIsActivated());
			userEntity.setUserType(model.getUserType());
			userEntity.setEmployCode(model.getEmployCode());
			userEntity.setUpdateDt(new Date());
			userEntity.setUpdateUser(model.getCurrentUser());
			userRepository.save(userEntity);

			this.insertUserScope(userEntity, model);

			return userEntity.getUuid();
		}
		return null;
	}

	@Transactional
	@Override
	public String insertUserRoleScope(UserUpdateRequest model) {
		UserEntity userEntity = new UserEntity();
		userEntity.setLoginAccount(model.getAccount());
		userEntity.setLoginSecretCode(bcryptEncoder.encode(model.getPassword()));
		userEntity.setStatus("I");
		userEntity.setUserName(model.getUserName());
		userEntity.setEmail(model.getEmail());
		userEntity.setUserType(model.getUserType());
		userEntity.setIsActivated(model.getIsActivated());
		userEntity.setEmployCode(model.getEmployCode());
		userEntity.setCreateDt(new Date());
		userEntity.setCreateUser(model.getCurrentUser());
		userEntity.setUpdateDt(new Date());
		userEntity.setUpdateUser(model.getCurrentUser());
		userRepository.save(userEntity);

		this.insertUserScope(userEntity, model);

		return userEntity.getUuid();
	}

	@Override
	public UserScopeResponse getUserScope(String userId) {
		UserScopeResponse result = new UserScopeResponse();
		UserEntity userEntity = userRepository.findByUuid(userId).orElse(null);

		if (userEntity != null) {
			result.setAccount(userEntity.getLoginAccount());
			result.setEmail(userEntity.getEmail());
			result.setUserName(userEntity.getUserName());
			result.setUserType(userEntity.getUserType());
			result.setUuid(userEntity.getUuid());
			result.setIsActivated(userEntity.getIsActivated());
			result.setEmployCode(userEntity.getEmployCode());

			List<UserRoleScopeUpdateRequest> roleScopes = new ArrayList<>();

			List<UserScopeEntity> userScopeEntities = userScopeRepository.findByUserUUID(userId);
			Map<Long, List<UserScopeEntity>> roleGroup = userScopeEntities.stream()
					.collect(Collectors.groupingBy(UserScopeEntity::getRoleSeqNo));

			if (UserTypeEnum.Tenant.toString().equals(userEntity.getUserType())) {
				roleGroup.entrySet().forEach(x -> {

					UserRoleScopeUpdateRequest roleScope = new UserRoleScopeUpdateRequest();
					Long roleCode = x.getKey();
					roleScope.setRoleCode(roleCode);

					List<UserRoleScopeTenantUpdateRequest> tenants = x.getValue().stream().map(item -> {

						UserRoleScopeTenantUpdateRequest userRoleScopeTenant = new UserRoleScopeTenantUpdateRequest();
						userRoleScopeTenant.setFacility(item.getFacilityCode());
						userRoleScopeTenant.setTenantCode(item.getTenantCode());
						userRoleScopeTenant.setOrgId(item.getOrgId());
						userRoleScopeTenant.setDept(item.getDept());
						userRoleScopeTenant.setCustomer(item.getCustomer());
						userRoleScopeTenant.setBrand(item.getBrand());
						return userRoleScopeTenant;
					}).collect(Collectors.toList());

					roleScope.setTenants(tenants);
					roleScopes.add(roleScope);
				});

			} else if (UserTypeEnum.Shipping.toString().equals(userEntity.getUserType())) {
				roleGroup.entrySet().forEach(x -> {
					UserRoleScopeUpdateRequest roleScope = new UserRoleScopeUpdateRequest();
					Long roleCode = x.getKey();
					List<String> facilities = x.getValue().stream().map(item -> item.getFacilityCode())
							.collect(Collectors.toList());
					roleScope.setRoleCode(roleCode);
					roleScope.setShippings(facilities);
					roleScopes.add(roleScope);
				});
			} else if (UserTypeEnum.Forwarder.toString().equals(userEntity.getUserType())) {
				roleGroup.entrySet().forEach(x -> {
					UserRoleScopeUpdateRequest roleScope = new UserRoleScopeUpdateRequest();
					Long roleCode = x.getKey();

					List<UserRoleScopeForwarderUpdateRequest> forwarders = x.getValue().stream().map(item -> {
						UserRoleScopeForwarderUpdateRequest userRoleScopeForwarder = new UserRoleScopeForwarderUpdateRequest();
						userRoleScopeForwarder.setTenantCode(item.getTenantCode());
						userRoleScopeForwarder.setForwarderCode(item.getForwarderCode());
						userRoleScopeForwarder.setFacility(item.getFacilityCode());
						return userRoleScopeForwarder;
					}).collect(Collectors.toList());

					roleScope.setRoleCode(roleCode);
					roleScope.setForwarders(forwarders);
					roleScopes.add(roleScope);
				});
			} else if (UserTypeEnum.Customer.toString().equals(userEntity.getUserType())) {
				roleGroup.entrySet().forEach(x -> {
					UserRoleScopeUpdateRequest roleScope = new UserRoleScopeUpdateRequest();
					Long roleCode = x.getKey();
					List<String> customers = x.getValue().stream().map(item -> item.getCustomer())
							.collect(Collectors.toList());
					roleScope.setCustomers(customers);
					roleScope.setRoleCode(roleCode);
					roleScopes.add(roleScope);
				});
			} else if (UserTypeEnum.SuperAdmin.toString().equals(userEntity.getUserType())) {
				roleGroup.entrySet().forEach(x -> {
					UserRoleScopeUpdateRequest roleScope = new UserRoleScopeUpdateRequest();
					Long roleCode = x.getKey();
					roleScope.setRoleCode(roleCode);
					roleScopes.add(roleScope);
				});
			}
			result.setRoleScopes(roleScopes);
		}

		return result;
	}

	/**
	 * update userScope
	 * 
	 * @param userEntity
	 * @param model
	 */
	private void insertUserScope(UserEntity userEntity, UserUpdateRequest model) {
		List<UserScopeEntity> userScopeEntities = new ArrayList<>();

		List<UserRoleScopeUpdateRequest> usefulRoleScope = model.getRoleScopes().stream().filter(x -> {
			return x.getRoleCode() != null;
		}).collect(Collectors.toList());

		// update email, uodateDt, updateUser in Notify.UserSubscription
		List<UserSubscriptionEntity> userSubscription = userSubscriptionRepository
				.findBySubscriberIdAndSendingMethod(userEntity.getLoginAccount(), "Email");
		userSubscription.stream().forEach(x -> {
			x.setSendingTarget(userEntity.getEmail());
			x.setUpdateDt(new Date());
			x.setUpdateUser(userEntity.getUpdateUser() == null ? "system" : userEntity.getUpdateUser());
			userSubscriptionRepository.save(x); 
		});
		
		//delete old mandatory subscription
		List<NotifyEventEntity> events = notifyEventRepository.findByIsUserVisible(0);
		List<Long> mandatoryEnventSeqNos = events.stream().map(NotifyEventEntity::getSeqNo).collect(Collectors.toList());
		userSubscriptionRepository.deleteBySubscriberIdAndNotifyEventSeqNoIn(userEntity.getLoginAccount(), mandatoryEnventSeqNos);

		if (UserTypeEnum.Tenant.toString().equals(userEntity.getUserType())) {
			usefulRoleScope.forEach(x -> {
				var role = roleRepository.findById(x.getRoleCode()).get();
				var subscriptions = mandatorySubscriptionRepository
						.findByUserTypeAndIsActivatedAndRoleCode(userEntity.getUserType(), 1, role.getRoleCode());

				x.getTenants().forEach(tenantObj -> {
					subscriptions.forEach(k -> {
						Arrays.asList(k.getFacilityCodeDefault().split(",")).forEach(z -> {
							NotifyEventContextEntity context = notifyEventContextRepository
									.findById(k.getNotifyEventContextSeqNo()).get();
							var userSubscriptionEntity = new UserSubscriptionEntity();
							userSubscriptionEntity.setNotifyEventContextSeqNo(k.getNotifyEventContextSeqNo());
							userSubscriptionEntity.setNotifyEventSeqNo(k.getNotifyEventSeqNo());
							userSubscriptionEntity.setSubscriberId(userEntity.getLoginAccount());
							userSubscriptionEntity.setSubscriberUserType(userEntity.getUserType());
							userSubscriptionEntity.setSendingMethod(context.getSendingMethod());
							userSubscriptionEntity.setSendingTarget(userEntity.getEmail());
							userSubscriptionEntity.setIsIncludeAttachment(context.getIsIncludeAttachment());
							userSubscriptionEntity.setAttachmentTypeList(context.getDefaultAttachmentTypeList());
							userSubscriptionEntity.setTenantCode(tenantObj.getTenantCode());
							userSubscriptionEntity.setCustomerCode(tenantObj.getCustomer());
							userSubscriptionEntity.setFacilityCode(z);
							userSubscriptionEntity.setIsByOwner(1);
							userSubscriptionEntity.setIsDeletable(k.getIsDeletableDefault());
							userSubscriptionEntity.setIsSwitchable(k.getIsSwitchableDefault());
							userSubscriptionEntity.setIsActivated(1);
							userSubscriptionEntity.setCreateDt(new Date());
							userSubscriptionEntity.setCreateUser("system");
							userSubscriptionEntity.setUpdateDt(new Date());
							userSubscriptionEntity.setUpdateUser(userEntity.getUpdateUser() == null ? "system" : userEntity.getUpdateUser());
							userSubscriptionRepository.save(userSubscriptionEntity);
						});
					});
				});

				List<UserScopeEntity> userScopes = x.getTenants().stream().map(tenant -> {
					UserScopeEntity userScopeEntity = new UserScopeEntity();
					userScopeEntity.setUserUUID(userEntity.getUuid());
					userScopeEntity.setRoleSeqNo(x.getRoleCode());
					userScopeEntity.setFacilityCode(tenant.getFacility());
					userScopeEntity.setTenantCode(tenant.getTenantCode());
					userScopeEntity.setOrgId(tenant.getOrgId());
					userScopeEntity.setDept(tenant.getDept());
					userScopeEntity.setBrand(tenant.getBrand());
					userScopeEntity.setCustomer(tenant.getCustomer());
					userScopeEntity.setCreateDt(new Date());
					userScopeEntity.setCreateUser("system");
					userScopeEntity.setUpdateDt(new Date());
					userScopeEntity.setUpdateUser("system");
					return userScopeEntity;
				}).collect(Collectors.toList());
				userScopeEntities.addAll(userScopes);
			});
		} else if (UserTypeEnum.Shipping.toString().equals(userEntity.getUserType())) {

			usefulRoleScope.forEach(x -> {

				var role = roleRepository.findById(x.getRoleCode()).get();
				var subscriptions = mandatorySubscriptionRepository
						.findByUserTypeAndIsActivatedAndRoleCode(userEntity.getUserType(), 1, role.getRoleCode());
				x.getShippings().forEach(item -> {
					subscriptions.stream().filter(j -> StringUtils.equals(j.getFacilityCodeScope(), item))
							.collect(Collectors.toList()).forEach(k -> {
								Arrays.asList(k.getFacilityCodeDefault().split(",")).forEach(z -> {
									if (z.equals(item)) {
										NotifyEventContextEntity context = notifyEventContextRepository
												.findById(k.getNotifyEventContextSeqNo()).get();
										var userSubscriptionEntity = new UserSubscriptionEntity();
										userSubscriptionEntity
												.setNotifyEventContextSeqNo(k.getNotifyEventContextSeqNo());
										userSubscriptionEntity.setNotifyEventSeqNo(k.getNotifyEventSeqNo());
										userSubscriptionEntity.setSubscriberId(userEntity.getLoginAccount());
										userSubscriptionEntity.setSubscriberUserType(userEntity.getUserType());
										userSubscriptionEntity.setSendingMethod(context.getSendingMethod());
										userSubscriptionEntity.setSendingTarget(userEntity.getEmail());
										userSubscriptionEntity.setIsIncludeAttachment(context.getIsIncludeAttachment());
										userSubscriptionEntity
												.setAttachmentTypeList(context.getDefaultAttachmentTypeList());
										userSubscriptionEntity.setFacilityCode(z);
										userSubscriptionEntity.setIsByOwner(1);
										userSubscriptionEntity.setIsDeletable(k.getIsDeletableDefault());
										userSubscriptionEntity.setIsSwitchable(k.getIsSwitchableDefault());
										userSubscriptionEntity.setIsActivated(1);
										userSubscriptionEntity.setCreateDt(new Date());
										userSubscriptionEntity.setCreateUser("system");
										userSubscriptionEntity.setUpdateDt(new Date());
										userSubscriptionEntity.setUpdateUser(userEntity.getUpdateUser() == null ? "system" : userEntity.getUpdateUser());
										userSubscriptionRepository.save(userSubscriptionEntity);
									}
								});
							});
				});

				List<UserScopeEntity> userScopes = x.getShippings().stream().map(facility -> {
					UserScopeEntity userScopeEntity = new UserScopeEntity();
					userScopeEntity.setUserUUID(userEntity.getUuid());
					userScopeEntity.setRoleSeqNo(x.getRoleCode());
					userScopeEntity.setFacilityCode(facility);
					userScopeEntity.setCreateDt(new Date());
					userScopeEntity.setCreateUser("system");
					userScopeEntity.setUpdateDt(new Date());
					userScopeEntity.setUpdateUser("system");
					return userScopeEntity;
				}).collect(Collectors.toList());
				userScopeEntities.addAll(userScopes);
			});
		} else if (UserTypeEnum.Forwarder.toString().equals(userEntity.getUserType())) {

			usefulRoleScope.forEach(x -> {

				var role = roleRepository.findById(x.getRoleCode()).get();
				var subscriptions = mandatorySubscriptionRepository
						.findByUserTypeAndIsActivatedAndRoleCode(userEntity.getUserType(), 1, role.getRoleCode());
				x.getForwarders().forEach(item -> {
					subscriptions.forEach(k -> {
						Arrays.asList(k.getFacilityCodeDefault().split(",")).forEach(z -> {
							if (z.equals(item.getFacility())) {
								NotifyEventContextEntity context = notifyEventContextRepository
										.findById(k.getNotifyEventContextSeqNo()).get();
								var userSubscriptionEntity = new UserSubscriptionEntity();
								userSubscriptionEntity.setNotifyEventContextSeqNo(k.getNotifyEventContextSeqNo());
								userSubscriptionEntity.setNotifyEventSeqNo(k.getNotifyEventSeqNo());
								userSubscriptionEntity.setSubscriberId(userEntity.getLoginAccount());
								userSubscriptionEntity.setSubscriberUserType(userEntity.getUserType());
								userSubscriptionEntity.setSendingMethod(context.getSendingMethod());
								userSubscriptionEntity.setSendingTarget(userEntity.getEmail());
								userSubscriptionEntity.setIsIncludeAttachment(context.getIsIncludeAttachment());
								userSubscriptionEntity.setAttachmentTypeList(context.getDefaultAttachmentTypeList());
								userSubscriptionEntity.setTenantCode(item.getTenantCode());
								userSubscriptionEntity.setFacilityCode(z);
								userSubscriptionEntity.setForwarderCode(item.getForwarderCode());
								userSubscriptionEntity.setIsByOwner(1);
								userSubscriptionEntity.setIsDeletable(k.getIsDeletableDefault());
								userSubscriptionEntity.setIsSwitchable(k.getIsSwitchableDefault());
								userSubscriptionEntity.setIsActivated(1);
								userSubscriptionEntity.setCreateDt(new Date());
								userSubscriptionEntity.setCreateUser("system");
								userSubscriptionEntity.setUpdateDt(new Date());
								userSubscriptionEntity.setUpdateUser(userEntity.getUpdateUser() == null ? "system" : userEntity.getUpdateUser());
								userSubscriptionRepository.save(userSubscriptionEntity);
							}
						});
					});
				});
				List<UserScopeEntity> userScopes = x.getForwarders().stream().map(forwarder -> {
					UserScopeEntity userScopeEntity = new UserScopeEntity();
					userScopeEntity.setUserUUID(userEntity.getUuid());
					userScopeEntity.setRoleSeqNo(x.getRoleCode());
					userScopeEntity.setForwarderCode(forwarder.getForwarderCode());
					userScopeEntity.setTenantCode(forwarder.getTenantCode());
					userScopeEntity.setFacilityCode(forwarder.getFacility());
					userScopeEntity.setCreateDt(new Date());
					userScopeEntity.setCreateUser("system");
					userScopeEntity.setUpdateDt(new Date());
					userScopeEntity.setUpdateUser("system");
					return userScopeEntity;
				}).collect(Collectors.toList());
				userScopeEntities.addAll(userScopes);
			});
		} else if (UserTypeEnum.Customer.toString().equals(userEntity.getUserType())) {
			usefulRoleScope.forEach(x -> {
				List<UserScopeEntity> userScopes = x.getCustomers().stream().map(customer -> {
					UserScopeEntity userScopeEntity = new UserScopeEntity();
					userScopeEntity.setUserUUID(userEntity.getUuid());
					userScopeEntity.setRoleSeqNo(x.getRoleCode());
					userScopeEntity.setCustomer(customer);
					userScopeEntity.setCreateDt(new Date());
					userScopeEntity.setCreateUser("system");
					userScopeEntity.setUpdateDt(new Date());
					userScopeEntity.setUpdateUser("system");
					return userScopeEntity;
				}).collect(Collectors.toList());
				userScopeEntities.addAll(userScopes);
			});
		} else if (UserTypeEnum.SuperAdmin.toString().equals(userEntity.getUserType())) {
			usefulRoleScope.forEach(x -> {
				UserScopeEntity userScopeEntity = new UserScopeEntity();
				userScopeEntity.setUserUUID(userEntity.getUuid());
				userScopeEntity.setRoleSeqNo(x.getRoleCode());
				userScopeEntity.setCreateDt(new Date());
				userScopeEntity.setCreateUser("system");
				userScopeEntity.setUpdateDt(new Date());
				userScopeEntity.setUpdateUser("system");
				userScopeEntities.add(userScopeEntity);
			});
		}

		List<UserRoleEntity> userRoleEntities = model.getRoleScopes().stream().map(x -> {
			UserRoleEntity userRoleEntity = new UserRoleEntity();
			userRoleEntity.setUserUUID(userEntity.getUuid());
			userRoleEntity.setRoleSeqNo(x.getRoleCode());
			userRoleEntity.setCreateUser(model.getCurrentUser());
			userRoleEntity.setCreateDt(new Date());
			userRoleEntity.setUpdateUser(model.getCurrentUser());
			userRoleEntity.setUpdateDt(new Date());
			return userRoleEntity;
		}).collect(Collectors.toList());

		userRoleRepository.deleteByUserUUID(userEntity.getUuid());
		userRoleRepository.saveAll(userRoleEntities);

		userScopeRepository.deleteByUserUUID(userEntity.getUuid());
		userScopeRepository.saveAll(userScopeEntities);
	}

	@Override
	public void changePassword(ChangePasswordRequest model) {
		UserEntity userEntity = userRepository.findByUuid(model.getUuid()).orElse(null);

		if (userEntity != null) {
			boolean isSame = bcryptEncoder.matches(model.getOldPassword(), userEntity.getLoginSecretCode());
			if (!isSame) {
				throw new ErrorCodeException(UserErrorCodeEnumImpl.PASSWORD_NOT_SAME);
			}

			userEntity.setLoginSecretCode(bcryptEncoder.encode(model.getNewPassword()));
			userEntity.setUpdateDt(new Date());
			userEntity.setUpdateUser(model.getLoginAccount());
			userRepository.save(userEntity);

		} else {
			throw new ErrorCodeException(UserErrorCodeEnumImpl.USER_NOT_FOUND);
		}

	}

	

}

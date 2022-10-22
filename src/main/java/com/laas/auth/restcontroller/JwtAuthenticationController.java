package com.laas.auth.restcontroller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laas.auth.configuration.JwtTokenUtil;
import com.laas.auth.model.args.CreateUserRequest;
import com.laas.auth.model.args.JwtRequest;
import com.laas.auth.model.dto.UserPermissionDto;
import com.laas.auth.model.dto.UserRoleDto;
import com.laas.auth.model.entity.UserEntity;
import com.laas.auth.model.entity.UserScopeEntity;
import com.laas.auth.model.resp.AuthorizationResponse;
import com.laas.auth.model.resp.JwtResponse;
import com.laas.auth.model.resp.JwtUserResponse;
import com.laas.auth.model.resp.UserDataScopeResp;
import com.laas.auth.model.resp.UserPermission;
import com.laas.auth.model.resp.UserProfile;
import com.laas.auth.model.resp.UserRoleResp;
import com.laas.auth.repository.UserRepository;
import com.laas.auth.service.Impl.JwtUserDetailsService;
import com.laas.auth.service.interfaces.PermissionService;
import com.laas.auth.service.interfaces.UserRoleService;
import com.laas.auth.service.interfaces.UserScopeService;

@RestController
@CrossOrigin
@RequestMapping(path = "/", produces = "application/json")
public class JwtAuthenticationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserScopeService userScopeService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/authentication")
	public ResponseEntity<JwtResponse> createAuthenticationToken (
	        @RequestBody JwtRequest authenticationRequest
    ) throws Exception {
		logger.info("createAuthenticationToken authenticationRequest = {}", authenticationRequest);
		if (authenticationRequest.getUserName().contains("@")) {
			String redirectUrl = userDetailsService
					.getRedirectUrlFromEmail(authenticationRequest.getUserName().split("@")[1]);
			if (StringUtils.isNotEmpty(redirectUrl)) {
				JwtResponse resp = new JwtResponse("");
				resp.setRedirectUrl(redirectUrl);
				logger.info("createAuthenticationToken resp = {}", resp);
				return ResponseEntity.ok(resp);
			}
		}
		authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		if (userDetailsService.checkUserPxd(authenticationRequest.getPassword(), userDetails.getPassword())) {
			final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest);
			logger.info("createAuthenticationToken JwtResponse(token) = {}", new JwtResponse(token));
			return ResponseEntity.ok(new JwtResponse(token));
		} else {
			throw new Exception("can't find user");
		}
	}

	@PostMapping(value = "/authenticationByEmail")
	public ResponseEntity<JwtResponse> authenticationByEmail (
	        @RequestBody JwtRequest authenticationRequest
    ) throws Exception {
		logger.info("authenticationByEmail authenticationRequest = {}", authenticationRequest);
	    final UserDetails userDetails = userDetailsService.loadUserByEmail(authenticationRequest.getUserName());
		final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest);
		logger.info("authenticationByEmail token = {}", token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@GetMapping(value = "/authorization")
	public ResponseEntity<AuthorizationResponse> authorization (
			@RequestHeader(name = "Authorization") String authorization
	) throws Exception {
		logger.info("authorization authorization = {}", authorization);
		String token = authorization.substring(7);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		Optional<UserEntity> user = userRepository.findByLoginAccount(username);
		if (user.isPresent()) {
			UserProfile profile = new UserProfile();
			BeanUtils.copyProperties(user.get(), profile);
			AuthorizationResponse response = new AuthorizationResponse();

			logger.info("authorization profile = {}", profile);
			
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
					scope.setBrand(item.getBrand());
					scope.setCustomer(item.getCustomer());
					scope.setDept(item.getDept());
					scope.setFacilityCode(item.getFacilityCode());
					scope.setForwarderCode(item.getForwarderCode());
					scope.setOrgId(item.getOrgId());
					scope.setTenantCode(item.getTenantCode());
					return scope;
				}).collect(Collectors.toList());
				x.setScopes(scopesProfile);
			});
			response.setUserProfile(profile);
			logger.info("authorization response = {}", response);

			return ResponseEntity.ok(response);
		} else {
			throw new Exception("can't find user");
		}
	}
	
	@GetMapping(value = "/authentication/user")
	public ResponseEntity<JwtUserResponse> getUserFromJwt (
			@RequestHeader(name = "Authorization") String authorization
	) throws Exception {
		logger.info("getUserFromJwt Authorization = {}", authorization);
		String token = authorization.substring(7);
		String loginAccount = jwtTokenUtil.getUsernameFromToken(token);
		String loginFacility = jwtTokenUtil.findFacilityFromToken(token);
	    String loginLanguage = jwtTokenUtil.findLanguageFromToken(token);
		String userType = jwtTokenUtil.findUserTypeFromToken(token);
		Optional<UserEntity> user = userRepository.findByLoginAccount(loginAccount);
		JwtUserResponse response = new JwtUserResponse();
		if(user.isPresent()) {
			response.setUuid(user.get().getUuid());
			response.setUserName(user.get().getUserName());
			response.setEmail(user.get().getEmail());
			response.setLoginFacility(loginFacility);
			response.setLoginLanguage(loginLanguage);
			response.setUserType(userType);
			logger.info("getUserFromJwt response = {}", response);
			return ResponseEntity.ok(response);
		}else {
			throw new Exception("can't find user");
		}
		
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserEntity> saveUser(@RequestBody CreateUserRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
//	
//	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
//	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
//		// From the HttpRequest get the claims
//		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
//
//		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
//		String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
//		return ResponseEntity.ok(new JwtResponse(token));
//	}

//	public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
//		Map<String, Object> expectedMap = new HashMap<String, Object>();
//		for (Entry<String, Object> entry : claims.entrySet()) {
//			expectedMap.put(entry.getKey(), entry.getValue());
//		}
//		return expectedMap;
//	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
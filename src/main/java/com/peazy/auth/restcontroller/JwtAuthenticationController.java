package com.peazy.auth.restcontroller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peazy.auth.configuration.JwtTokenUtil;
import com.peazy.auth.model.args.CreateUserRequest;
import com.peazy.auth.model.args.JwtRequest;
import com.peazy.auth.model.entity.UserEntity;
import com.peazy.auth.model.response.AuthorizationResponse;
import com.peazy.auth.model.response.JwtResponse;
import com.peazy.auth.model.response.UserProfile;
import com.peazy.auth.service.impl.UserDetailsServiceImpl;
import com.peazy.auth.service.interfaces.JwtAuthenticationService;
import com.peazy.auth.service.interfaces.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin
@RequestMapping(path = "/", produces = "application/json")
public class JwtAuthenticationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(value = "/authentication")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByEmail(authenticationRequest.getUserEmail());
		if (userDetailsServiceImpl.checkUserPassword(authenticationRequest.getUserPassword(), userDetails.getPassword())) {
			final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest);
			return ResponseEntity.ok(new JwtResponse(token));
		} else {
			throw new Exception("Can't find the user");
		}
    }

    @GetMapping(value = "/authorization")
    public ResponseEntity<AuthorizationResponse> authorization(
            @RequestHeader(name = "Authorization") String authorization) throws Exception {
        return jwtAuthenticationService.authorization(authorization);
    }

    @PostMapping(value = "/createUser")
	public ResponseEntity<UserEntity> createUser(@RequestBody CreateUserRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsServiceImpl.save(user));
	}

    @GetMapping(value = "/greeting")
    public String greeting() {
        return "Hi";
    }
}
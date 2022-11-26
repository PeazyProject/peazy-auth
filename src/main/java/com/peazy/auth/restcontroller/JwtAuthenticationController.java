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
import com.peazy.auth.model.args.CreateCustomerUserRequest;
import com.peazy.auth.model.args.JwtRequest;
import com.peazy.auth.model.entity.CustomerUserEntity;
import com.peazy.auth.model.resp.AuthorizationResponse;
import com.peazy.auth.model.resp.JwtResponse;
import com.peazy.auth.model.resp.UserProfile;
import com.peazy.auth.service.Impl.UserDetailsServiceImpl;
import com.peazy.auth.service.interfaces.CustomerUserService;

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
    private CustomerUserService customerUserService;

    @PostMapping(value = "/authentication")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUserName());
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
        String token = authorization.substring(7);
        String username = jwtTokenUtil.getUserNameFromToken(token);
        Optional<CustomerUserEntity> user = customerUserService.findByUserName(username);
        logger.info("user={}", user);
        if (user.isPresent()) {
            UserProfile profile = new UserProfile();
            BeanUtils.copyProperties(user.get(), profile);
            AuthorizationResponse response = new AuthorizationResponse();
            response.setUserProfile(profile);
            return ResponseEntity.ok(response);
        } else {
            throw new Exception("Can't find the user");
        }
    }

    @PostMapping(value = "/registerCustomerUser")
	public ResponseEntity<CustomerUserEntity> saveUser(@RequestBody CreateCustomerUserRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsServiceImpl.save(user));
	}

    @GetMapping(value = "/greeting")
    public String greeting() {
        return "Hi";
    }
}
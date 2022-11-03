package com.peazy.auth.restcontroller;

import java.util.HashMap;

import javax.security.auth.message.AuthException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.peazy.auth.model.resp.JwtResponse;
import com.peazy.auth.service.Impl.UserDetailsServiceImpl;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class JwtAuthenticationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody HashMap<String, String> user) {
    // JwtTokenUtil jwtToken = new JwtTokenUtil();
    // String token = jwtToken.generateToken(user); // 取得token
    // return ResponseEntity.status(HttpStatus.OK).body(token);
    // }

    @PostMapping(value = "/authentication")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        logger.info("createAuthenticationToken authenticationRequest = {}", authenticationRequest);
        logger.info("email={}, password={}", authenticationRequest.getUserEmail(), authenticationRequest.getUserPassword());
        // authenticate(authenticationRequest.getUserEmail(), authenticationRequest.getUserPassword());
        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getUserName());
		if (userDetailsServiceImpl.checkUserPassword(authenticationRequest.getUserPassword(), userDetails.getPassword())) {
			final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest);
			logger.info("createAuthenticationToken JwtResponse(token) = {}", new JwtResponse(token));
			return ResponseEntity.ok(new JwtResponse(token));
		} else {
			throw new Exception("can't find user");
		}

    }

    @PostMapping(value = "/registerCustomerUser")
	public ResponseEntity<CustomerUserEntity> saveUser(@RequestBody CreateCustomerUserRequest user) throws Exception {
		return ResponseEntity.ok(userDetailsServiceImpl.save(user));
	}

    private void authenticate(String userEmail, String userPassword) throws Exception {
        logger.info("myInfo "+userPassword+" "+bcryptEncoder.encode(userPassword));
        logger.info("myInfo "+userPassword+" "+bcryptEncoder.encode(userPassword));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, bcryptEncoder.encode(userPassword)));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @GetMapping(value = "/greeting")
    public String greeting() {
        return "Hi";
    }
}

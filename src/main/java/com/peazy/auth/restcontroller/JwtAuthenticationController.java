package com.peazy.auth.restcontroller;

import java.util.HashMap;

import javax.security.auth.message.AuthException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peazy.auth.configuration.JwtTokenUtil;
import com.peazy.auth.model.args.JwtRequest;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class JwtAuthenticationController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestBody HashMap<String, String> user) {
    //     JwtTokenUtil jwtToken = new JwtTokenUtil();
    //     String token = jwtToken.generateToken(user); // 取得token
    //     return ResponseEntity.status(HttpStatus.OK).body(token);
    // }

    // @PostMapping(value = "/authentication")
    // public ResponseEntity createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

    //     logger.info("createAuthenticationToken authenticationRequest = {}", authenticationRequest);
    //     if (authenticationRequest.getUserName().contains("@")) {
	// 		String redirectUrl = userDetailsService
	// 				.getRedirectUrlFromEmail(authenticationRequest.getUserName().split("@")[1]);
	// 		if (StringUtils.isNotEmpty(redirectUrl)) {
	// 			JwtResponse resp = new JwtResponse("");
	// 			resp.setRedirectUrl(redirectUrl);
	// 			logger.info("createAuthenticationToken resp = {}", resp);
	// 			return ResponseEntity.ok(resp);
	// 		}
	// 	}
    //     String token = au.substring(6);
    //     JwtTokenUtil jwtToken = new JwtTokenUtil();
    //     try {
    //         jwtToken.validateToken(token);
    //     } catch (AuthException e) {
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    //     }
    //     return ResponseEntity.status(HttpStatus.OK).body("Hello CaiLi");
    // }

    @GetMapping(value = "/greeting")
    public String greeting() {
        return "Hi";
    }
}

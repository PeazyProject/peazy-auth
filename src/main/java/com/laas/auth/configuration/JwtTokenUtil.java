package com.laas.auth.configuration;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.laas.auth.model.args.JwtRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Component
public class JwtTokenUtil implements Serializable{
    
	private static final long serialVersionUID = -2550185165626007488L;
	
	@Value("${jwt.secret}")
	private String secret; 
	
	@Value("${jwt.expirationDateInMs}")
	private int jwtExpirationInMs;
	
	private static final String CLAIM_NAME_FACILITY = "facility";
	private static final String CLAIM_NAME_LANGUAGE = "language";
	private static final String CLAIM_NAME_USER_TYPE = "userType";

	// retrieve user name from JWT token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	} 
	
	public String findFacilityFromToken(String token) {
	    return getClaimFromToken(token, claims -> 
            claims.get(CLAIM_NAME_FACILITY, String.class)
         );
    }
	
	public String findLanguageFromToken(String token) {
        return getClaimFromToken(token, claims -> 
            claims.get(CLAIM_NAME_LANGUAGE, String.class)
         );
    }

	public String findUserTypeFromToken(String token) {
        return getClaimFromToken(token, claims -> 
            claims.get(CLAIM_NAME_USER_TYPE, String.class)
         );
    }

	// retrieve expiration date from JWT token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	} 

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	} 

	// generate token for user
	public String generateToken(UserDetails userDetails, JwtRequest jwtRequest) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_NAME_FACILITY, jwtRequest.getFacility());
	    claims.put(CLAIM_NAME_LANGUAGE, jwtRequest.getLanguage());
		claims.put(CLAIM_NAME_USER_TYPE, jwtRequest.getUserType());
		return doGenerateToken(claims, userDetails.getUsername());
	} 

	// while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    // compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	} 

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
	
	public String doGenerateRefreshToken(String token,UserDetails userDetails) {
		Claims claims = getAllClaimsFromToken(token);
		return this.doGenerateRefreshToken(claims, userDetails.getUsername());
	}
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
		return this.doGenerateToken(claims, subject);

	}
	
	private Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
		Map<String, Object> expectedMap = new HashMap<>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
	}
	
}
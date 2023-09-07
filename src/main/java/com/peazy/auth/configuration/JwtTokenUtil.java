package com.peazy.auth.configuration;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.peazy.auth.model.args.JwtRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

    private static final long EXPIRATION_TIME = 1 * 60 * 30000;

    private static final String SECRET = "peazy";

    private static final String CLAIM_NAME_USEREMAIL = "userEmail";
    private static final String CLAIM_NAME_USERNAME = "userName";
    private static final String CLAIM_NAME_USERPASSWORD = "userPassword";

    public String generateToken(UserDetails userDetails, JwtRequest jwtRequest) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_NAME_USEREMAIL, jwtRequest.getUserEmail());
        claims.put(CLAIM_NAME_USERPASSWORD, jwtRequest.getUserPassword());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean validateTokenByEmail(String token, UserDetails userDetails) {
        final String email = getUserEmailFromToken(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.getExpiration());
    }

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get(CLAIM_NAME_USERNAME, String.class));
    }

    // test method
    public String getUserEmailFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get(CLAIM_NAME_USEREMAIL, String.class));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public String doGenerateRefreshToken(String token,UserDetails userDetails) {
		Claims claims = getAllClaimsFromToken(token);
		return this.generateRefreshToken(claims, userDetails.getUsername());
	}

    public String generateRefreshToken(Map<String, Object> claims, String subject) {
		return this.doGenerateToken(claims, subject);
	}

}
